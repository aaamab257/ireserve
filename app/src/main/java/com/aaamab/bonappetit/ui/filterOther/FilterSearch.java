package com.aaamab.bonappetit.ui.filterOther;

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
import com.aaamab.bonappetit.data.AreaFilters;
import com.aaamab.bonappetit.data.Cuisines;
import com.aaamab.bonappetit.data.FilterCity;
import com.aaamab.bonappetit.data.FilterData;
import com.aaamab.bonappetit.data.FilterObj;
import com.aaamab.bonappetit.data.MallItem;
import com.aaamab.bonappetit.data.ZoneItem;
import com.aaamab.bonappetit.databinding.ActivityFilterSearchBinding;
import com.aaamab.bonappetit.ui.adapter.OtherFilterAdapter;
import com.aaamab.bonappetit.ui.filter.FilterScreen;
import com.aaamab.bonappetit.ui.government.GovernmentFilter;
import com.aaamab.bonappetit.ui.government.Selectedinter;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.searchResult.SearchResult;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class FilterSearch extends AppCompatActivity implements FilterOtherInterface, Selectedinter {
    FilterSearchHandler handler;
    ActivityFilterSearchBinding binding;
    FilterOtherPresenter presenter;
    OtherFilterAdapter adapter;
    AreaFilters dataSe;
    ArrayList<Cuisines> filteredList;
    ArrayList<MallItem> malls;
    ArrayList<ZoneItem> area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter_search);
        handler = new FilterSearchHandler(this);
        binding.setHandler(handler);
        presenter = new FilterOtherPresenter(this);
        getBun();

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
    public void getBun() {
        Bundle bundle = getIntent().getExtras();
        String ty = bundle.getString("type");
        if (ty.equals("area")) {
            //getAreaMall
            StaticMethods.ZoneIds = new ArrayList<>();
            if (StaticMethods.data != null){
                area = StaticMethods.data.data.zone;
            }else {
                area = StaticMethods.areaMallData.data.zone;
            }
            //presenter.onArea(this , StaticMethods.GovernmentIds);
            binding.title.setText(R.string.area);
            adapter = new OtherFilterAdapter(this, StaticMethods.data, 1, this, StaticMethods.areaMallData);
            binding.recGovernment.setLayoutManager(new LinearLayoutManager(this));
            binding.recGovernment.setAdapter(adapter);
            binding.edSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString(),"1");
                }
            });
        } else if (ty.equals("cuis")) {
            StaticMethods.CuisIds = new ArrayList<>();
            binding.title.setText(R.string.cuisines);
            if (StaticMethods.data != null){
                filteredList = StaticMethods.data.data.cuisines;
            }else {
                filteredList = StaticMethods.areaMallData.data.cuisines;
            }
            //presenter.onCuis(this , StaticMethods.Cuisines);
            adapter = new OtherFilterAdapter(this, StaticMethods.data, 3, this, StaticMethods.areaMallData);
            binding.recGovernment.setLayoutManager(new LinearLayoutManager(this));
            binding.recGovernment.setAdapter(adapter);
            binding.edSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString(),"3");
                }
            });
        } else if (ty.equals("mall")) {
            //getMall
            StaticMethods.MallIds = new ArrayList<>();
            binding.title.setText(R.string.mall);
            if (StaticMethods.data != null){
                malls = StaticMethods.data.data.mallss;
            }else {
                malls = StaticMethods.areaMallData.data.mallss;
            }
            //presenter.onMall(this ,StaticMethods.ZoneIds);
            adapter = new OtherFilterAdapter(this, StaticMethods.data, 2, this, StaticMethods.areaMallData);
            binding.recGovernment.setLayoutManager(new LinearLayoutManager(this));
            binding.recGovernment.setAdapter(adapter);
            binding.edSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString(),"2");
                }
            });
        }
    }

    @Override
    public void onArea(AreaFilters data) {
        Log.e("TAG", "onArea: " + "Success");

    }

    @Override
    public void onMall(AreaFilters data) {

    }

    @Override
    public void onCuis(FilterData<FilterObj> data) {

    }

    @Override
    public void onFail(String error) {

    }

    @Override
    public void onConnection(boolean isConnected) {

    }

    @Override
    public void onSelected(String name, String id) {
        if (name.equals("area")) {
            StaticMethods.ZoneIds.add(id);
        } else if (name.equals("mall")) {
            StaticMethods.MallIds.add(id);
        } else if (name.equals("cuis")) {
            StaticMethods.CuisIds.add(id);
        }
    }

    @Override
    public void onRemoved(String name, String id) {
        if (name.equals("area")) {
            for (int i = 0; i < StaticMethods.ZoneIds.size(); i++) {
                if (id == StaticMethods.ZoneIds.get(i)) {
                    StaticMethods.ZoneIds.remove(i);
                }
            }

        } else if (name.equals("mall")) {
            for (int i = 0; i < StaticMethods.MallIds.size(); i++) {
                if (id == StaticMethods.MallIds.get(i)) {
                    StaticMethods.MallIds.remove(i);
                }
            }

        } else if (name.equals("cuis")) {
            for (int i = 0; i < StaticMethods.CuisIds.size(); i++) {
                if (id == StaticMethods.CuisIds.get(i)) {
                    StaticMethods.CuisIds.remove(i);
                }
            }
        }

    }

    public class FilterSearchHandler {
        Context context;

        public FilterSearchHandler(Context context) {
            this.context = context;
        }

        public void onBack(View view) {
            onBackPressed();
        }

        public void onApply(View view) {
            Intent resultIntent = new Intent();
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
            //IntentUtilies.openActivityInNewStack(FilterSearch.this, FilterScreen.class);
        }
        public void home(View v){
            IntentUtilies.openActivityInNewStack(FilterSearch.this, MainScreen.class);
        }

        public void onCancel(View v) {
            onBackPressed();
        }
    }

    private void filter(String text, String type) {
        if (type.equals("1")){
            ArrayList<ZoneItem> filteredLists = new ArrayList<>();

            for (ZoneItem item : area) {
                if (item.getName_en().toLowerCase().contains(text.toLowerCase())) {
                    filteredLists.add(item);
                }
            }

            adapter.filterList(null,null,filteredLists);
        }else if (type.equals("2")){
            ArrayList<MallItem> filteredListMall = new ArrayList<>();

            for (MallItem item : malls) {
                if (item.getName_en().toLowerCase().contains(text.toLowerCase())) {
                    filteredListMall.add(item);
                }
            }

            adapter.filterList(null ,filteredListMall,null);
        }else if(type.equals("3")){
            ArrayList<Cuisines> filteredListCuis = new ArrayList<>();

            for (Cuisines item : filteredList) {
                if (item.getName_en().toLowerCase().contains(text.toLowerCase())) {
                    filteredListCuis.add(item);
                }
            }

            adapter.filterList(filteredListCuis ,null,null);
        }
        /**/

    }
}