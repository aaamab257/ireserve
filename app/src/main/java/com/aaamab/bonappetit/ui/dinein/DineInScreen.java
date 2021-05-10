package com.aaamab.bonappetit.ui.dinein;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FilterCity;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.ResturantsData;
import com.aaamab.bonappetit.data.Upcomming;
import com.aaamab.bonappetit.databinding.FragmentDineInScreenBinding;
import com.aaamab.bonappetit.ui.adapter.DineInAdapter;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;

import java.util.ArrayList;


public class DineInScreen extends Fragment implements DineInHomeInter {

    FragmentDineInScreenBinding binding;
    View v;
    DineInAdapter adapter;
    DineInHandler handler;
    CustomDialog dialog;
    DineInHomePresenter presenter;
    ArrayList<ResturantsData> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dine_in_screen, container, false);
        v = binding.getRoot();
        handler = new DineInHandler(getActivity());
        binding.setHandler(handler);
        presenter = new DineInHomePresenter(this);
        dialog = new CustomDialog(getActivity());
       // dialog.showDialog();

        presenter.getDineIn(getActivity(), "D");

        return v;
    }

    @Override
    public void onSuccess(RestArray array) {
        if (array.getData().size() == 0){
            binding.txtNoData.setText(R.string.no_data);
        }else {
            String type = "D";
            StaticMethods.restArray = array ;
            data = array.getData();
            adapter = new DineInAdapter(getActivity(),array , type, "M");
            binding.recDineIn.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recDineIn.setAdapter(adapter);
        }

    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(getActivity() , error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(getActivity() , R.string.noConnection);
    }

    @Override
    public void onUpComing(Upcomming array) {

    }

    @Override
    public void isRated(boolean isRated) {

    }

    public class DineInHandler {
        Context context;

        public DineInHandler(Context context) {
            this.context = context;
        }
    }


}