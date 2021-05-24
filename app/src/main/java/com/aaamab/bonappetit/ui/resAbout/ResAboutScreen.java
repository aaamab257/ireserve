package com.aaamab.bonappetit.ui.resAbout;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FoodData;
import com.aaamab.bonappetit.data.RemoveFood;
import com.aaamab.bonappetit.data.RemoveFoodOBJ;
import com.aaamab.bonappetit.data.RestMenu;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.databinding.FragmentResAboutScreenBinding;
import com.aaamab.bonappetit.ui.menus.MenuInter;
import com.aaamab.bonappetit.ui.menus.MenuPresenter;
import com.aaamab.bonappetit.ui.resDetails.RestaurantDetails;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ResAboutScreen extends Fragment implements MenuInter, OnMapReadyCallback {

    FragmentResAboutScreenBinding binding;
    View v;
    ResHandler handler;
    MenuPresenter presenter;
    private GoogleMap mMap;
    double lat, lng;
    String name = " ";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_res_about_screen, container, false);
        v = binding.getRoot();
        handler = new ResHandler(getActivity());
        binding.setHandler(handler);
        presenter = new MenuPresenter(this);
        presenter.getDetails(getActivity(), StaticMethods.resID);
        return v;
    }

    @Override
    public void onSuccess(RestruantByID restruant) {
        binding.phone.setText(restruant.getRes().get(0).getPhone());
        //binding.hours.setText("Daily "+restruant.getRes().get(0).getFrom_date()+" - "+restruant.getRes().get(0).getTo_date());
        binding.address.setText(restruant.getRes().get(0).getAddress());
        lat = restruant.getRes().get(0).getLatitude();
        lng = restruant.getRes().get(0).getLongitude();
        name = restruant.getRes().get(0).getName();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ((RestaurantDetails)getActivity()).goneButtons(true);
    }

    @Override
    public void onFail(String error) {

    }

    @Override
    public void onConnection(boolean isConnected) {

    }

    @Override
    public void onApply(boolean status, RemoveFood<RemoveFoodOBJ> data) {

    }

    @Override
    public void onFood(FoodData data) {

    }

    @Override
    public void onMenuFood(RestMenu data) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(sydney).title(name));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17), 200, null);
    }

    public class ResHandler {
        Context context;

        public ResHandler(Context context) {
            this.context = context;
        }

    }
}