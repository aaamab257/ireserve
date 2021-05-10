package com.aaamab.bonappetit.ui.searchResult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.databinding.ActivitySearchResultBinding;
import com.aaamab.bonappetit.ui.adapter.DineInAdapter;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.paymentMethod.PaymentMethodScreen;
import com.aaamab.bonappetit.ui.resDetails.RestaurantDetails;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;

public class SearchResult extends AppCompatActivity {
    ResultHandler handler ;
    ActivitySearchResultBinding binding;
    DineInAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_search_result);
        handler = new ResultHandler(this);
        binding.setHandler(handler);
        StaticMethods.type = "W";
        if (StaticMethods.restArray.getData().size() == 0 ){
            binding.txtNoData.setText(R.string.no_data);
        }else {
            adapter = new DineInAdapter(this , StaticMethods.restArray , "W" , "S");
            binding.recResta.setLayoutManager(new LinearLayoutManager(this));

            binding.recResta.setAdapter(adapter);
        }

    }
    public void goToResDetails(int id){
        Bundle b = new Bundle();
        b.putInt("id" , id);
        IntentUtilies.openActivityWithBundle(SearchResult.this , RestaurantDetails.class , b);
    }
    public class ResultHandler{
        Context context ;

        public ResultHandler(Context context) {
            this.context = context;
        }
        public void onBack(View v){
            onBackPressed();
            StaticMethods.restArray = null ;
            finish();
        }
        public void home(View v){
            IntentUtilies.openActivityInNewStack(SearchResult.this, MainScreen.class);
        }
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
}