package com.aaamab.bonappetit.ui.about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.AboutArray;
import com.aaamab.bonappetit.data.TermsArray;
import com.aaamab.bonappetit.databinding.ActivityAboutScreenBinding;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;

public class AboutScreen extends AppCompatActivity implements AboutInter {
    AboutHandler handler ;
    ActivityAboutScreenBinding binding ;
    AboutArray aboutArray ;
    AboutPresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_about_screen);
        handler = new AboutHandler(this);
        binding.setHandler(handler);
        presenter = new AboutPresenter(this);
        presenter.getAbout(this);
    }

    @Override
    public void onSuccess(AboutArray aboutArray) {
        this.aboutArray = aboutArray;
        binding.txtOurStory.setText(aboutArray.getAboutitems().get(0).getAbout_us_en());
        binding.txtLocation.setText(aboutArray.getAboutitems().get(0).getLocation_en());
    }

    @Override
    public void onFail(String error) {
        ToastUtil.showErrorToast(this , error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        ToastUtil.showErrorToast(this , R.string.noConnection);
    }

    @Override
    public void onTerms(TermsArray array) {

    }

    public class AboutHandler{
        Context context ;

        public AboutHandler(Context context) {
            this.context = context;
        }

        public void onBack(View view){
            onBackPressed();
        }
        public void facebook(View view){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(aboutArray.getAboutitems().get(0).getFacebook()));
            startActivity(browserIntent);
        }
        public void twitter(View view){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(aboutArray.getAboutitems().get(0).getTwiter()));
            startActivity(browserIntent);
        }
        public void insta(View view){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(aboutArray.getAboutitems().get(0).getInsta()));
            startActivity(browserIntent);
        }
        public void youtube(View view){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(aboutArray.getAboutitems().get(0).getYoutube()));
            startActivity(browserIntent);
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
}