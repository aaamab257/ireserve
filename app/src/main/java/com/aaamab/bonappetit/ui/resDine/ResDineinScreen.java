package com.aaamab.bonappetit.ui.resDine;

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
import com.aaamab.bonappetit.databinding.FragmentResDineinScreenBinding;
import com.aaamab.bonappetit.ui.adapter.DineInAdapter;
import com.aaamab.bonappetit.ui.adapter.UpcomingAdapter;
import com.aaamab.bonappetit.ui.dinein.DineInHomeInter;
import com.aaamab.bonappetit.ui.dinein.DineInHomePresenter;
import com.aaamab.bonappetit.ui.resPickup.ResPickupScreen;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class ResDineinScreen extends Fragment implements DineInHomeInter {



    View v;
    DineInAdapter adapter;
    CustomDialog dialog;
    DineInHomePresenter presenter;
    FragmentResDineinScreenBinding binding ;
    ResDineHandler handler ;
    BottomSheetBehavior sheetBehavior;
    UpcomingAdapter adapterUp ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_res_dinein_screen, container, false);
        v = binding.getRoot();
        dialog = new CustomDialog(getActivity());
        handler = new ResDineHandler(getActivity());

        binding.setHandler(handler);
        sheetBehavior = BottomSheetBehavior.from(binding.layoutBottomSheet);
        presenter = new DineInHomePresenter(this);
        //dialog.showDialog();
        presenter.getVip(getActivity(), "D");
        presenter.getUp(getActivity());
        return v;
    }

    @Override
    public void onSuccess(RestArray array) {
        dialog.dismissDialog();
        adapter = new DineInAdapter(getActivity() , array , "D", "M");
        binding.recDineIn.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recDineIn.setAdapter(adapter);
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
        if (array.data.size() > 0){
            adapterUp = new UpcomingAdapter(getActivity() , array);
            binding.recUp.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recUp.setAdapter(adapterUp);
        }else {
            binding.upcomingTxt.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void isRated(boolean isRated) {

    }

    public class ResDineHandler{
        Context context ;

        public ResDineHandler(Context context) {
            this.context = context;
        }

        public void toggleBottomSheet(View v){
            if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
    }
}