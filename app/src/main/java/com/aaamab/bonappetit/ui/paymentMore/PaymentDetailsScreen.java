package com.aaamab.bonappetit.ui.paymentMore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.PaymentMoreData;
import com.aaamab.bonappetit.databinding.ActivityPaymentDetailsScreenBinding;
import com.aaamab.bonappetit.ui.adapter.PaymentDetailsAdapter;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;

public class PaymentDetailsScreen extends AppCompatActivity implements PaymentMoreInter {
    ActivityPaymentDetailsScreenBinding binding ;
    PaymentHandler handler ;
    Presenter presenter;
    PaymentDetailsAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_payment_details_screen);
        handler = new PaymentHandler(this);
        binding.setHandler(handler);
        presenter = new Presenter(this);
        presenter.getPayments(this);

    }

    @Override
    public void onSuccess(PaymentMoreData data) {
        if (data.data.size() == 0 ){
            binding.txtNoData.setVisibility(View.VISIBLE);
        }else {
            adapter = new PaymentDetailsAdapter(this,data);
            binding.recPayments.setLayoutManager(new LinearLayoutManager(this));
            binding.recPayments.setAdapter(adapter);
        }

    }

    @Override
    public void onFail(String error) {
        Log.e("TAG", "onFail: "+error );
        ToastUtil.showErrorToast(this, error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        ToastUtil.showErrorToast(this, R.string.noConnection);
    }

    public class PaymentHandler{
        Context context ;

        public PaymentHandler(Context context) {
            this.context = context;
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