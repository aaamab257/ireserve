package com.aaamab.bonappetit.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.databinding.ActivitySplashScreenBinding;
import com.aaamab.bonappetit.helpers.prefs.PrefUtils;
import com.aaamab.bonappetit.ui.login.LoginScreen;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;

public class SplashScreen extends AppCompatActivity {
    Handler mHandler;
    ActivitySplashScreenBinding binding ;
    SplashHandler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_splash_screen);
        handler = new SplashHandler(this);
        binding.setHandler(handler);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PrefUtils.getUserformation(SplashScreen.this)) {
                    if (StaticMethods.userData.getApi_token() == null) {
                        IntentUtilies.openActivityInNewStack(SplashScreen.this, LoginScreen.class);
                    } else {
                        IntentUtilies.openActivityInNewStack(SplashScreen.this, MainScreen.class);
                    }
                }else {
                    IntentUtilies.openActivityInNewStack(SplashScreen.this, LoginScreen.class);
                }

            }
        }, 4000);
    }

    public class SplashHandler{
        Context context ;

        public SplashHandler(Context context) {
            this.context = context;
        }
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
}