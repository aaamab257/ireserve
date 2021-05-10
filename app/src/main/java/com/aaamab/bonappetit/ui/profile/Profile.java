package com.aaamab.bonappetit.ui.profile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.LoginData;
import com.aaamab.bonappetit.data.ProfileData;
import com.aaamab.bonappetit.data.ProfileDataPra;
import com.aaamab.bonappetit.databinding.ActivityProfileBinding;
import com.aaamab.bonappetit.helpers.prefs.PrefUtils;
import com.aaamab.bonappetit.ui.login.LoginScreen;
import com.aaamab.bonappetit.ui.splash.SplashScreen;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtilResponse;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity implements ProfileInter, DialogUtilResponse {
    ProfileHandler handler;
    ActivityProfileBinding binding;
    ProfilePresenter presenter;
    CustomDialog dialog;
    DialogUtil dialogUtil;
    DataObj<ProfileData<ProfileDataPra>> data;
    AlertDialog.Builder dialogBuilder;
    View dialogView;
    AlertDialog alertDialog ;
    EditText genderE ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        handler = new ProfileHandler(this);
        dialogUtil = new DialogUtil(this);
        binding.setHandler(handler);

        presenter = new ProfilePresenter(this);
        dialog = new CustomDialog(this);
        //dialog.showDialog();
        presenter.profileData(this);
        dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        dialogView = inflater.inflate(R.layout.edit_profile, null);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        genderE = dialogView.findViewById(R.id.gender);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
    @Override
    public void onSuccess(DataObj<ProfileData<ProfileDataPra>> data) {
        dialog.dismissDialog();
        this.data = data;
        binding.txtSience.setText(getString(R.string.member_since) + data.data.getCreated_at());
        binding.numRes.setText("" + data.data.reservation_number);
        binding.numReviews.setText("" + data.data.reviews_number);
        binding.txtEmail.setText("" + data.data.userData.getEmail());
        binding.txtAddress.setText("" + data.data.userData.getAddress());
        binding.txtGender.setText("" + data.data.userData.getGender());
        binding.txtPhone.setText("" + data.data.userData.getPhone());
        binding.txtUserName.setText("" + data.data.userData.getName().toUpperCase());
    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(Profile.this,  error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(Profile.this, R.string.noConnection);
    }

    @Override
    public void onChangePass(boolean status) {
        dialog.dismissDialog();
        ToastUtil.showSuccessToast(Profile.this, R.string.pass_changed);
    }

    @Override
    public void onDataChanged(DataObj<LoginData> userData) {
        dialog.dismissDialog();
        StaticMethods.ClearChash();
        PrefUtils.SignOut_User(Profile.this);
        StaticMethods.userData = userData.data;
        PrefUtils.saveUserinformation(this, userData.data, PrefUtils.User_Singin);
        //ToastUtil.showSuccessToast(Profile.this, "Your data changed");
        binding.txtEmail.setText("" + userData.data.getEmail());
        binding.txtAddress.setText("" + userData.data.getAddress());
        binding.txtGender.setText("" + userData.data.getGender());
        binding.txtPhone.setText("" + userData.data.getPhone());
        binding.txtUserName.setText("" + userData.data.getName().toUpperCase());

    }

    @Override
    public void selectedValueSingleChoice(int position) {

    }

    @Override
    public void selectedValueSingleChoice(int position, String arrayType) {
        if (position == 0) {
            genderE.setText(R.string.male);
        }else {
            genderE.setText(R.string.female);
        }
    }

    @Override
    public void selectedMultiChoicelang(ArrayList<String> choices, ArrayList<String> postions, String arrayType) {

    }

    public class ProfileHandler {
        Context context;

        public ProfileHandler(Context context) {
            this.context = context;
        }

        public void onBack(View v) {
            onBackPressed();
        }

        public void onRestPass(View v) {
            openResetPassDialog();
        }

        public void onEdit(View v) {
            openEditDialog();
        }

        public void onLogout(View v) {
            StaticMethods.ClearChash();
            PrefUtils.SignOut_User(Profile.this);
            IntentUtilies.openActivityInNewStack(Profile.this, LoginScreen.class);
        }

    }

    private void openResetPassDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.rest_pass_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Button change = dialogView.findViewById(R.id.btn_change_pass);
        ImageView close = dialogView.findViewById(R.id.close);
        final EditText old = dialogView.findViewById(R.id.old);
        final EditText newPass = dialogView.findViewById(R.id.newPass);
        final EditText confirmNew = dialogView.findViewById(R.id.confirmNew);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassS = old.getText().toString();
                String newPassS = newPass.getText().toString();
                String confirmPassS = confirmNew.getText().toString();

                callRestAPI(oldPassS, newPassS, confirmPassS);
                alertDialog.dismiss();
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }

    private void openEditDialog() {

        Button change = dialogView.findViewById(R.id.btn_save);
        ImageView close = dialogView.findViewById(R.id.close);
        final EditText nameE = dialogView.findViewById(R.id.name);
        final EditText emailE = dialogView.findViewById(R.id.email);
        final EditText phoneE = dialogView.findViewById(R.id.phone);
        final EditText locationE = dialogView.findViewById(R.id.location);
        final EditText genderE = dialogView.findViewById(R.id.gender);
        nameE.setText(data.data.userData.getName());
        emailE.setText(data.data.userData.getEmail());
        phoneE.setText(data.data.userData.getPhone());
        locationE.setText(data.data.userData.getAddress());
        genderE.setText(data.data.userData.getGender());

        genderE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> gende;
                gende = new ArrayList<>();
                gende.add(Profile.this.getString(R.string.male));
                gende.add(Profile.this.getString(R.string.female));
                dialogUtil.showDialogSingleChooice(Profile.this, getString(R.string.gender), R.string.ok, gende, "type");
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameE.getText().toString();
                String email = emailE.getText().toString();
                String phone = phoneE.getText().toString();
                String gender = genderE.getText().toString();
                String location = locationE.getText().toString();
                callEditAPI(name, email, phone, gender, location);
                alertDialog.dismiss();
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }

    private void callEditAPI(String name, String email, String phone, String gender, String location) {
        //dialog.showDialog();
        presenter.changeData(Profile.this, name, email, phone, location, gender);
    }

    private void callRestAPI(String oldPassS, String newPassS, String confirmPassS) {
        //dialog.showDialog();
        presenter.changePass(Profile.this, oldPassS, newPassS, confirmPassS);
    }
}

