package com.aaamab.bonappetit.ui.support;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.SupportArray;
import com.aaamab.bonappetit.databinding.ActivitySupportScreenBinding;
import com.aaamab.bonappetit.ui.adapter.SupportAdapter;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;

public class SupportScreen extends AppCompatActivity implements SupportInterface {
    ActivitySupportScreenBinding binding ;
    SupportHandler handler ;
    SupportAdapter adapter ;
    SupportPresenter presenter ;
    CustomDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_support_screen);
        handler = new SupportHandler(this);
        presenter = new SupportPresenter(this);
        dialog = new CustomDialog(this);
        //dialog.showDialog();
        presenter.getSupport(this);
        binding.setHandler(handler);
    }

    @Override
    public void onSuccess(SupportArray array) {
        dialog.dismissDialog();
        adapter = new SupportAdapter(SupportScreen.this , array);
        binding.recSupport.setLayoutManager(new LinearLayoutManager(this));
        binding.recSupport.setAdapter(adapter);
    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(this , error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(this , R.string.noConnection);
    }

    public class SupportHandler{
        Context context ;

        public SupportHandler(Context context) {
            this.context = context;
        }

        public void onBack(View view){
            onBackPressed();
        }
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
}