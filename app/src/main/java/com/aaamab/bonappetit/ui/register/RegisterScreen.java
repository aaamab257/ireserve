package com.aaamab.bonappetit.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.RegisterData;
import com.aaamab.bonappetit.databinding.ActivityRegisterScreenBinding;
import com.aaamab.bonappetit.ui.profile.Profile;
import com.aaamab.bonappetit.ui.termsAndConditions.ConditionsScreen;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtilResponse;

import java.util.ArrayList;

public class RegisterScreen extends AppCompatActivity implements RegInter , DialogUtilResponse {
    ActivityRegisterScreenBinding binding;
    RegisterHandler handler;
    RegPresenter presenter;
    CustomDialog dialog;
    DialogUtil dialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_screen);
        handler = new RegisterHandler(this);
        binding.setHandler(handler);
        presenter = new RegPresenter(this);
        dialog = new CustomDialog(this);
        dialogUtil = new DialogUtil(this);
    }

    @Override
    public void onSuccess(RegisterData data) {
        dialog.dismissDialog();
        ToastUtil.showSuccessToast(this , R.string.account_created);
        onBackPressed();
        finish();
    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showSuccessToast(this , error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showSuccessToast(this , R.string.noConnection);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
    @Override
    public void selectedValueSingleChoice(int position) {

    }

    @Override
    public void selectedValueSingleChoice(int position, String arrayType) {
        if (position == 0) {
            binding.edGender.setText(R.string.male);
        }else {
            binding.edGender.setText(R.string.female);
        }
    }

    @Override
    public void selectedMultiChoicelang(ArrayList<String> choices, ArrayList<String> postions, String arrayType) {

    }

    public class RegisterHandler {
        Context context;

        public RegisterHandler(Context context) {
            this.context = context;
        }

        public void onBack(View v) {
            onBackPressed();
        }

        public void terms(View v) {
            IntentUtilies.openActivity(RegisterScreen.this, ConditionsScreen.class);
        }

        public void submit(View v) {
           // dialog.showDialog();
            presenter.registerFun(RegisterScreen.this, binding.edEmail.getText().toString(), binding.edPhone.getText().toString(),
                    binding.edPass.getText().toString(), binding.edUsername.getText().toString(), binding.edPassConfirm.getText().toString() ,
                    binding.edAddress.getText().toString() ,binding.edGender.getText().toString() );
        }

        public void gender(View v){
            ArrayList<String> gende;
            gende = new ArrayList<>();
            gende.add(RegisterScreen.this.getString(R.string.male));
            gende.add(RegisterScreen.this.getString(R.string.female));
            dialogUtil.showDialogSingleChooice(RegisterScreen.this, getString(R.string.gender), R.string.ok, gende, "type");
        }
    }
}