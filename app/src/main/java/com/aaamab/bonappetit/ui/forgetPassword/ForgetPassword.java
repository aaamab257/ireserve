package com.aaamab.bonappetit.ui.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.databinding.ActivityForgetPasswordBinding;
import com.aaamab.bonappetit.utils.LocaleManager;

public class ForgetPassword extends AppCompatActivity {
    ActivityForgetPasswordBinding binding ;
    ForgetHandler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_forget_password);
        handler = new ForgetHandler(this);
        binding.setHandler(handler);
    }

    public class ForgetHandler{
        Context context ;

        public ForgetHandler(Context context) {
            this.context = context;
        }

        public void onDone(View v){

        }
        public void onBack(View v){
            onBackPressed();
        }
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
}