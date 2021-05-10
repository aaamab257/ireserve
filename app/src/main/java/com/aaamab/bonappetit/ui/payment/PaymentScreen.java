package com.aaamab.bonappetit.ui.payment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.databinding.ActivityPaymentScreenBinding;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;

import java.net.URL;
import java.net.URLConnection;

public class PaymentScreen extends AppCompatActivity {
    ActivityPaymentScreenBinding binding;
    PaymentHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_screen);
        handler = new PaymentHandler(this);
        binding.setHandler(handler);
        getBu();
    }

    private void getBu() {
        Bundle bu = getIntent().getExtras();
        String url = bu.getString("url");
        binding.webPayment.getSettings().setLoadsImagesAutomatically(true);
        binding.webPayment.getSettings().setJavaScriptEnabled(true);
        binding.webPayment.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Configure the client to use when opening URLs
        binding.webPayment.setWebViewClient(new WebViewClient());
        // Load the initial URL
        binding.webPayment.loadUrl(url);
        binding.webPayment.getSettings().setUseWideViewPort(true);
// Zoom out if the content width is greater than the width of the viewport
        binding.webPayment.getSettings().setLoadWithOverviewMode(true);
        binding.webPayment.getSettings().setSupportZoom(true);
        binding.webPayment.getSettings().setBuiltInZoomControls(true);
        binding.webPayment.getSettings().setDisplayZoomControls(false);

        binding.webPayment.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("paymentFail")) {
                    openFailPayment();
                } else if (url.contains("paymentSuccess")) {
                    openResetPassDialog();
                }
            }
        });
        /*if (url.contains("paymentFail")){
            ToastUtil.showErrorToast(PaymentScreen.this , "Payment Fail");
        }else {
            *//*ToastUtil.showErrorToast(PaymentScreen.this , "Payment Fail");*//*
        }*/
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }

    public class PaymentHandler {
        Context context;

        public PaymentHandler(Context context) {
            this.context = context;
        }

        public void onBack(View v) {
            onBackPressed();
        }
    }

    private void openResetPassDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.DialogThemePayment);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.payment_successfully, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Button change = dialogView.findViewById(R.id.btn_change_pass);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                IntentUtilies.openActivityInNewStack(PaymentScreen.this, MainScreen.class);
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }

    private void openFailPayment() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.DialogThemePayment);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fail_paymen_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Button cancel = dialogView.findViewById(R.id.btnCancel);
        Button tryAgain = dialogView.findViewById(R.id.btn_tryAgain);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                IntentUtilies.openActivityInNewStack(PaymentScreen.this, MainScreen.class);
            }
        });
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                onBackPressed();
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }
}