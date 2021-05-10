package com.aaamab.bonappetit.ui.boooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.AboutArray;
import com.aaamab.bonappetit.data.BookingArray;
import com.aaamab.bonappetit.data.TermsArray;
import com.aaamab.bonappetit.databinding.ActivityBookingScreenBinding;
import com.aaamab.bonappetit.ui.about.AboutInter;
import com.aaamab.bonappetit.ui.about.AboutPresenter;
import com.aaamab.bonappetit.ui.adapter.BookingsAdapter;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;

public class BookingScreen extends AppCompatActivity implements BookingsInter, AboutInter, OnTerms {
    BookingHandler handler;
    ActivityBookingScreenBinding binding;
    BookingsPresenter presenter;
    BookingsAdapter adapter;
    CustomDialog dialog;
    AboutPresenter presenterTer;
    String pho = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_screen);
        handler = new BookingHandler(this);
        binding.setHandler(handler);
        presenter = new BookingsPresenter(this);
        dialog = new CustomDialog(this);
        //dialog.showDialog();
        presenter.getBooking(this);

    }

    @Override
    public void onSuccess(BookingArray array) {

        if (array.bookingItems.size() ==0 ){
            binding.txtNoData.setVisibility(View.VISIBLE);
        }else {
            adapter = new BookingsAdapter(this, array, this);
            binding.recBookings.setLayoutManager(new LinearLayoutManager(this));
            binding.recBookings.setAdapter(adapter);
        }

    }

    @Override
    public void onSuccess(AboutArray aboutArray) {

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(this, error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(this, R.string.noConnection);
    }

    @Override
    public void onTerms(TermsArray array) {
        openEditDialog(array.getTermsItems().get(0).getPolicy_term_en());
    }

    @Override
    public void onClickTerms(boolean clicked) {
        presenterTer = new AboutPresenter(this);
        presenterTer.getTerms(this);
    }

    @Override
    public void onCancel(boolean cancel) {
        openCancelDialog();
    }

    @Override
    public void onEdit(boolean edit) {
        openEdit();
    }

    public class BookingHandler {
        Context context;

        public BookingHandler(Context context) {
            this.context = context;
        }

        public void onBack(View view) {
            onBackPressed();
        }
    }

    private void openEditDialog(String termsS) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.terms_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        ImageView close = dialogView.findViewById(R.id.close);

        final TextView terms = dialogView.findViewById(R.id.txtTerms);
        terms.setText(termsS);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }

    private void openCancelDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.edit_cancel_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        ImageView close = dialogView.findViewById(R.id.close);

        final TextView terms = dialogView.findViewById(R.id.txtTitle);
        terms.setText(R.string.contact_to_cancel);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }

    private void openEdit() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.edit_cancel_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        ImageView close = dialogView.findViewById(R.id.close);

        final TextView terms = dialogView.findViewById(R.id.txtTitle);
        terms.setText(R.string.contact_to_edit);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }

    public void onCall(String phone) {
        this.pho = phone;
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    0);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onCall(pho);
                } else {
                    ToastUtil.showErrorToast(BookingScreen.this, R.string.per_text);
                }
                break;

            default:
                break;
        }
    }
}