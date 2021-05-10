package com.aaamab.bonappetit.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.LoginData;
import com.aaamab.bonappetit.databinding.ActivityLoginBinding;
import com.aaamab.bonappetit.helpers.prefs.PrefUtils;
import com.aaamab.bonappetit.ui.forgetPassword.ForgetPassword;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.register.RegisterScreen;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;

public class LoginScreen extends AppCompatActivity implements LoginInter {
    ActivityLoginBinding binding;
    LoginHandler handler;
    LoginPresenter presenter;
    CustomDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        handler = new LoginHandler(this);
        binding.setHandler(handler);
        dialog = new CustomDialog(this);
        presenter = new LoginPresenter(this);


    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
    @Override
    public void onSuccess(LoginData data) {
        dialog.dismissDialog();
        ToastUtil.showSuccessToast(this, R.string.login_success);
        StaticMethods.userData = data;
        PrefUtils.saveUserinformation(this, data, PrefUtils.User_Singin);
        IntentUtilies.openActivity(LoginScreen.this, MainScreen.class);
        finish();
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

    public class LoginHandler {
        Context context;

        public LoginHandler(Context context) {
            this.context = context;
        }

        public void onLogin(View v) {
           // dialog.showDialog();
            presenter.loginFun(LoginScreen.this, binding.edPhoneEmail.getText().toString(), binding.edPassword.getText().toString());

        }

        public void onRegister(View v) {
            IntentUtilies.openActivity(LoginScreen.this, RegisterScreen.class);
        }

        public void onForgetPass(View v) {
            IntentUtilies.openActivity(LoginScreen.this, ForgetPassword.class);
        }
    }
}