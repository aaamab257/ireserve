package com.aaamab.bonappetit.ui.paymentOrderDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.databinding.ActivityPaymentOrderDetailsBinding;
import com.aaamab.bonappetit.ui.adapter.FoodPaymentAdapter;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.orderFoods.OrderFoodsScreen;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.google.gson.annotations.SerializedName;

public class PaymentOrderDetails extends AppCompatActivity {
    ActivityPaymentOrderDetailsBinding binding ;
    PaymentOrderDetailsHandler paymentOrderDetailshandler;
    FoodPaymentAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_order_details);
        paymentOrderDetailshandler = new PaymentOrderDetailsHandler(this);
        binding.setHandler(paymentOrderDetailshandler);
        getBun();
        adapter = new FoodPaymentAdapter(this, StaticMethods.getOrderFood );
        binding.recFoods.setLayoutManager(new LinearLayoutManager(this));
        binding.recFoods.setAdapter(adapter);
    }

    private void getBun() {
        Bundle intent = getIntent().getExtras();
        String services = String.valueOf(intent.getFloat("services" , 1));
        binding.txtServiceCharge.setText(services);
        String Subtotal = String.valueOf(intent.getFloat("Subtotal" , 1));
        binding.txtSubTotal.setText(Subtotal);

        String id = String.valueOf(intent.getInt("id" , 1));
        binding.orderId.setText("#"+id);
        String date = intent.getString("date" );
        binding.txtDate.setText(date);

        String total = String.valueOf(intent.getFloat("total" ));
        binding.txtTotalAmount.setText(total);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
    public class PaymentOrderDetailsHandler {
        Context context ;

        public PaymentOrderDetailsHandler(Context context) {
            this.context = context;
        }

        public void onBack(View v){
            onBackPressed();
        }
        public void home(View v){
            IntentUtilies.openActivityInNewStack(PaymentOrderDetails.this, MainScreen.class);
        }
    }
}