package com.aaamab.bonappetit.ui.government;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FilterCity;
import com.aaamab.bonappetit.data.FilterData;
import com.aaamab.bonappetit.data.FilterObj;
import com.aaamab.bonappetit.databinding.ActivityGovernmentFilterBinding;
import com.aaamab.bonappetit.ui.adapter.FilterAdapter;
import com.aaamab.bonappetit.ui.filter.FilterScreen;
import com.aaamab.bonappetit.ui.filterOther.FilterSearch;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class GovernmentFilter extends AppCompatActivity implements GovFilterInterface ,Selectedinter {
    GoverHandler handler ;
    ActivityGovernmentFilterBinding binding ;
    FilterAdapter adapter ;
    GoverFilterPresenter presenter ;
    ArrayList<String> names ;
    ArrayList<String> ids ;
    ArrayList<FilterCity> dataCity ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_government_filter);
        handler = new GoverHandler(this);


        binding.setHandler(handler);
        presenter = new GoverFilterPresenter(this);
        presenter.onGovernment(this);
        names = new ArrayList<>();
        ids = new ArrayList<>();
        binding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
    @Override
    public void onSuccess(FilterData<FilterObj> data) {
        StaticMethods.Cuisines = data.getData().getCuisines();
        StaticMethods.Zones = data.getData().getZone();
        StaticMethods.Mall = data.getData().getMallss();
        StaticMethods.data = data ;
        dataCity = data.data.getCity();
        adapter = new FilterAdapter(this,data,0,this);
        binding.recGovernment.setLayoutManager(new LinearLayoutManager(this));
        binding.recGovernment.setAdapter(adapter);
    }

    @Override
    public void onFail(String error) {
        ToastUtil.showErrorToast(this, error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        ToastUtil.showErrorToast(this, R.string.noConnection);
    }

    @Override
    public void onSelected(String name, String id) {
        names.add(name);
        ids.add(id);
    }

    @Override
    public void onRemoved(String name, String id) {
        for (int i = 0 ; i < names.size() ; i++){
            if(id.equals(ids.get(i)) ){
                ids.remove(i);
                names.remove(i);
            }
        }
    }

    public class GoverHandler{
        Context context ;

        public GoverHandler(Context context) {
            this.context = context;
        }
        public void home(View v){
            IntentUtilies.openActivityInNewStack(GovernmentFilter.this, MainScreen.class);
        }
        public void onBack(View v){
            onBackPressed();
        }
        public void onApply(View v){
            Log.e("TAG", "onApply Name: "+names);
            Log.e("TAG", "onApply IDS :  "+ids);
            StaticMethods.GovernmentNames = names ;
            StaticMethods.GovernmentIds = ids;
            Intent resultIntent = new Intent();
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
        public void onReset(View v){
            if (names.size() > 0){
                names.clear();
                ids.clear();
                recreate();
            }

        }
    }
    private void filter(String text) {
        ArrayList<FilterCity> filteredList = new ArrayList<>();

        for (FilterCity item : dataCity) {
            if (item.getName_en().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }
}