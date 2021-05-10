package com.aaamab.bonappetit.ui.curbsideHome;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.Upcomming;
import com.aaamab.bonappetit.databinding.FragmentCurbsideHomeBinding;
import com.aaamab.bonappetit.ui.adapter.DineInAdapter;
import com.aaamab.bonappetit.ui.dinein.DineInHomeInter;
import com.aaamab.bonappetit.ui.dinein.DineInHomePresenter;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;


public class CurbsideHome extends Fragment implements DineInHomeInter {

    FragmentCurbsideHomeBinding binding ;
    View v;
    DineInAdapter adapter;
    CurbHandler handler;
    CustomDialog dialog;
    DineInHomePresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_curbside_home, container, false);
        v = binding.getRoot();
        handler = new CurbHandler(getActivity());
        binding.setHandler(handler);
        presenter = new DineInHomePresenter(this);
        dialog = new CustomDialog(getActivity());
        //dialog.showDialog();
        presenter.getDineIn(getActivity(), "C");

        return v;
    }

    @Override
    public void onSuccess(RestArray array) {
        if (array.getData().size() == 0){
            binding.txtNoData.setText(R.string.no_data);
        }else {
            String type = "C";
            adapter = new DineInAdapter(getActivity(),array , type , "M");
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

    public class CurbHandler{
        Context context ;

        public CurbHandler(Context context) {
            this.context = context;
        }
    }
}