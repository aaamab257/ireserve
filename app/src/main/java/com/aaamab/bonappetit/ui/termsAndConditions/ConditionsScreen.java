package com.aaamab.bonappetit.ui.termsAndConditions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.AboutArray;
import com.aaamab.bonappetit.data.TermsArray;
import com.aaamab.bonappetit.databinding.ActivityConditionsScreenBinding;
import com.aaamab.bonappetit.ui.about.AboutInter;
import com.aaamab.bonappetit.ui.about.AboutPresenter;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;

public class ConditionsScreen extends AppCompatActivity implements AboutInter {
    CondHandler handler ;
    AboutPresenter presenter ;

    ActivityConditionsScreenBinding binding ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_conditions_screen);
        handler = new CondHandler(this);
        binding.setHandler(handler);
        presenter = new AboutPresenter(this);
        presenter.getTerms(this);
    }

    @Override
    public void onSuccess(AboutArray aboutArray) {

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
        binding.txtTerms.setText(array.getTermsItems().get(0).getPolicy_term_en());
    }

    public class CondHandler{
        Context context ;

        public CondHandler(Context context) {
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