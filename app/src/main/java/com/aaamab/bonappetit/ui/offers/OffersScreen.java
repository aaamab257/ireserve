package com.aaamab.bonappetit.ui.offers;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.OffersData;
import com.aaamab.bonappetit.databinding.FragmentOffersScreenBinding;
import com.aaamab.bonappetit.ui.adapter.OffersAdapter;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.StaticMethods;


public class OffersScreen extends Fragment implements OffersInter {

    FragmentOffersScreenBinding binding;
    View v;
    OffersHandler handler;
    OffersAdapter adapter;
    OfferPresenter presenter;
    CustomDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_offers_screen, container, false);
        v = binding.getRoot();
        handler = new OffersHandler(getActivity());
        binding.setHandler(handler);
        presenter = new OfferPresenter(this);
        dialog = new CustomDialog(getActivity());
        //dialog.showDialog();
        presenter.getOffers(StaticMethods.userData.api_token, getActivity());
        return v;
    }

    @Override
    public void onSuccess(OffersData data) {
        dialog.dismissDialog();
        if (data.getData().size() == 0) {
            binding.txtNoData.setVisibility(View.VISIBLE);
        } else {
            adapter = new OffersAdapter(getActivity(), data);
            binding.recOffers.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recOffers.setAdapter(adapter);
        }
    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();

    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
    }

    public class OffersHandler {
        Context context;

        public OffersHandler(Context context) {
            this.context = context;
        }
    }
}