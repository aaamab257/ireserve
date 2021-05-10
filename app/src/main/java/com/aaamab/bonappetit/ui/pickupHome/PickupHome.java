package com.aaamab.bonappetit.ui.pickupHome;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.Upcomming;
import com.aaamab.bonappetit.databinding.FragmentPickupHomeBinding;
import com.aaamab.bonappetit.ui.adapter.DineInAdapter;
import com.aaamab.bonappetit.ui.dinein.DineInHomeInter;
import com.aaamab.bonappetit.ui.dinein.DineInHomePresenter;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;

import static android.content.ContentValues.TAG;


public class PickupHome extends Fragment implements DineInHomeInter {
    View v;
    DineInAdapter adapter;
    CustomDialog dialog;
    DineInHomePresenter presenter;
    FragmentPickupHomeBinding binding ;
    PickUpHandler handler ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pickup_home, container, false);
        v = binding.getRoot();
        handler = new PickUpHandler(getActivity());
        binding.setHandler(handler);
        presenter = new DineInHomePresenter(this);
        dialog = new CustomDialog(getActivity());
        //dialog.showDialog();
        presenter.getDineIn(getActivity(), "P");

        return v;
    }

    @Override
    public void onSuccess(RestArray array) {
        Log.e(TAG, "onSuccess: "+array.getData().size() );
        if (array.getData().size() == 0){
            binding.txtNoData.setText(R.string.no_data);
        }else {
            dialog.dismissDialog();
            String type = "P";
            adapter = new DineInAdapter(getActivity(),array,type, "M");
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

    public class PickUpHandler{
        Context context ;

        public PickUpHandler(Context context) {
            this.context = context;
        }
    }
}