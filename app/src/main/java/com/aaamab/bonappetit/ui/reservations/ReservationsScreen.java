package com.aaamab.bonappetit.ui.reservations;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.ResturantsData;
import com.aaamab.bonappetit.data.Upcomming;
import com.aaamab.bonappetit.databinding.FragmentReservationsScreenBinding;
import com.aaamab.bonappetit.ui.adapter.DineInAdapter;
import com.aaamab.bonappetit.ui.adapter.ResPagerAdapter;
import com.aaamab.bonappetit.ui.adapter.viewPagerAdapters.HomePagerAdapter;
import com.aaamab.bonappetit.ui.dinein.DineInHomeInter;
import com.aaamab.bonappetit.ui.dinein.DineInHomePresenter;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.search.SearchScreen;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class ReservationsScreen extends Fragment implements DineInHomeInter {

    ResPagerAdapter pagerAdapter ;
    ReserHandler handler ;
    FragmentReservationsScreenBinding binding;
    View v ;
    FragmentManager fragmentManager;
    ArrayList<ResturantsData> data;
    DineInHomePresenter presenter;
    DineInAdapter adapter ;
    int pagerPos = 0 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reservations_screen, container, false);
        v = binding.getRoot();
        handler  = new ReserHandler(getActivity());
        binding.setHandler(handler);
        configTabs() ;
        binding.txtHello.setText(StaticMethods.userData.getName().toUpperCase());
        presenter = new DineInHomePresenter(this);
        /*if (StaticMethods.userData.getGender().equals("female")){
            binding.imageView.setImageResource(R.drawable.female);
        }*/
        presenter.getVip(getActivity(), "D");
        pagerPos = binding.pager.getCurrentItem();
        Log.e(TAG, "onCreateView: "+pagerPos );
        binding.edSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerPos = binding.pager.getCurrentItem();
                Log.e(TAG, "onClick: "+pagerPos );
                if (pagerPos == 0){
                    presenter.getVip(getActivity(), "D");
                }else if (pagerPos == 1){
                    presenter.getVip(getActivity(), "C");
                }else {
                    presenter.getVip(getActivity(), "P");
                }
            }
        });
        Picasso.with(getActivity()).load(StaticMethods.userData.getImage()).into(binding.imageView);

        binding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                /*pagerPos = binding.pager.getCurrentItem();
                if (pagerPos == 0){
                    presenter.getDineIn(getActivity(), "D");
                }else if (pagerPos == 1){
                    presenter.getDineIn(getActivity(), "C");
                }else {
                    presenter.getDineIn(getActivity(), "P");
                }*/
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()){
                    binding.recSearch.setVisibility(View.VISIBLE);
                    binding.pager.setVisibility(View.GONE);
                }else {
                    binding.pager.setVisibility(View.VISIBLE);
                    binding.recSearch.setVisibility(View.GONE);
                    //configTabs();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


                filter(s.toString());
            }
        });
        return v;
    }
    private void configTabs() {
        pagerAdapter = new ResPagerAdapter(getChildFragmentManager(), getActivity());
        binding.tabLayout.setupWithViewPager(binding.pager);
        binding.pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onSuccess(RestArray array) {

        if (pagerPos == 0){
            Log.e(TAG, "onSuccess: 0 "+array.code );
            data = array.getData();
            adapter = new DineInAdapter(getActivity(),array , "D", "M");
            binding.recSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recSearch.setAdapter(adapter);
        }else if (pagerPos == 1){
            Log.e(TAG, "onSuccess: 1 "+array.code );
            data = array.getData();
            adapter = new DineInAdapter(getActivity(),array , "C", "M");
            binding.recSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recSearch.setAdapter(adapter);
        }else {
            Log.e(TAG, "onSuccess: 2 "+array.code );
            data = array.getData();
            adapter = new DineInAdapter(getActivity(),array , "P", "M");
            binding.recSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recSearch.setAdapter(adapter);
        }
    }

    @Override
    public void onFail(String error) {
        data = new ArrayList<>();
    }

    @Override
    public void onConnection(boolean isConnected) {

    }

    @Override
    public void onUpComing(Upcomming array) {

    }

    @Override
    public void isRated(boolean isRated) {

    }

    public class ReserHandler{
        Context context ;
        public ReserHandler(Context context) {
            this.context = context;
        }
        public void search(View v){
            fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.replace(R.id.fragment_container, new SearchScreen());
            fragmentTransaction.commit();
        }

        public void profile(View v ){
            ((MainScreen)getActivity()).profile();
        }
        public void onFilter(View v){
            ((MainScreen)getActivity()).filter();
        }
    }
    public void filter(String text) {
        ArrayList<ResturantsData> filteredList = new ArrayList<>();

        StaticMethods.printJson("data " , data);
        for (ResturantsData item : data) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

}