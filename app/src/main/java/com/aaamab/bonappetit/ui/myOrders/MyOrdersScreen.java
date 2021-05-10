package com.aaamab.bonappetit.ui.myOrders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.AllMyOrders;
import com.aaamab.bonappetit.databinding.ActivityMyOrdersScreenBinding;
import com.aaamab.bonappetit.ui.adapter.MyOrdersAdapter;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;

public class MyOrdersScreen extends AppCompatActivity implements MyOrdersInter{
    ActivityMyOrdersScreenBinding binding ;
    MyOrdersHandler handler ;
    MyOrdersPresenter presenter ;
    MyOrdersAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_orders_screen);
        handler = new MyOrdersHandler(this);

        binding.setHandler(handler);
        presenter = new MyOrdersPresenter(this);
        presenter.getMyOrders(this);
    }

    @Override
    public void onSuccess(AllMyOrders allMyOrders) {
        if (allMyOrders.data.size() == 0 ){
            binding.txtNoData.setVisibility(View.VISIBLE);
        }else {
            adapter = new MyOrdersAdapter(MyOrdersScreen.this , allMyOrders);
            binding.recMyOrders.setLayoutManager(new LinearLayoutManager(this));
            binding.recMyOrders.setAdapter(adapter);
        }

    }

    @Override
    public void onFail(String error) {
        ToastUtil.showErrorToast(this , error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        ToastUtil.showErrorToast(this , R.string.noConnection);
    }

    public class MyOrdersHandler{
        Context context ;

        public MyOrdersHandler(Context context) {
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