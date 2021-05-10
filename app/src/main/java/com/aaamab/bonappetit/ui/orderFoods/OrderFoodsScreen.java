package com.aaamab.bonappetit.ui.orderFoods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.databinding.ActivityOrderFoodsScreenBinding;
import com.aaamab.bonappetit.ui.adapter.FoodAdapter;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;

public class OrderFoodsScreen extends AppCompatActivity {
    OrderHandler handler ;
    ActivityOrderFoodsScreenBinding binding ;
    FoodAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_order_foods_screen);
        handler = new OrderHandler(this);
        binding.setHandler(handler);
        adapter = new FoodAdapter(this, StaticMethods.foods);
        binding.recFoods.setLayoutManager(new LinearLayoutManager(this));
        binding.recFoods.setAdapter(adapter);
    }

    public class OrderHandler{
        Context context;

        public OrderHandler(Context context) {
            this.context = context;
        }

        public void onBack(View v){
            onBackPressed();
            StaticMethods.foods = null;
        }

        public void home(View v){
            IntentUtilies.openActivityInNewStack(OrderFoodsScreen.this, MainScreen.class);
        }

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
}