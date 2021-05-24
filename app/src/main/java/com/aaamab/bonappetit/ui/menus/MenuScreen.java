package com.aaamab.bonappetit.ui.menus;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FoodData;
import com.aaamab.bonappetit.data.RemoveFood;
import com.aaamab.bonappetit.data.RemoveFoodOBJ;
import com.aaamab.bonappetit.data.RestMenu;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.databinding.FragmentMenuScreenBinding;
import com.aaamab.bonappetit.ui.adapter.MenuAdapter;
import com.aaamab.bonappetit.ui.adapter.NewMenuAdapter;
import com.aaamab.bonappetit.ui.imageDisplay.ImageDisplay;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.pickup.PickupScreen;
import com.aaamab.bonappetit.ui.resDetails.RestaurantDetails;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;


public class MenuScreen extends Fragment implements MenuInter, OnMapReadyCallback, onImageClicked {


    FragmentMenuScreenBinding binding;
    View v;
    MenuHandler handler;
    MenuPresenter presenter;
    CustomDialog dialog;
    MenuAdapter adapter;
    private GoogleMap mMap;
    double lat, lng;
    MapView mapView;
    RestruantByID restruantByID;
    String name = " ";
    NewMenuAdapter adapterMenu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu_screen, container, false);
        v = binding.getRoot();
        handler = new MenuHandler(getActivity());
        binding.setHandler(handler);
        presenter = new MenuPresenter(this);
        dialog = new CustomDialog(getActivity());
        ((RestaurantDetails)getActivity()).goneButtons(false);
        //dialog.showDialog();
        switch (StaticMethods.type) {

            case "C":
                binding.btnC.setVisibility(View.VISIBLE);
                binding.btnD.setVisibility(View.GONE);
                binding.btnP.setVisibility(View.GONE);
                break;
            case "P":
                binding.btnC.setVisibility(View.GONE);
                binding.btnD.setVisibility(View.GONE);
                binding.btnP.setVisibility(View.VISIBLE);
                break;
            case "D":
                binding.btnC.setVisibility(View.GONE);
                binding.btnD.setVisibility(View.VISIBLE);
                binding.btnP.setVisibility(View.GONE);
                break;
            case "W":
                binding.btnC.setVisibility(View.VISIBLE);
                binding.btnD.setVisibility(View.VISIBLE);
                binding.btnP.setVisibility(View.VISIBLE);

        }
        presenter.getDetails(getActivity(), StaticMethods.resID);
        presenter.getMenu(getActivity(), StaticMethods.resID);
        presenter.getMenuData(getActivity() , StaticMethods.resID);

        return v;
    }

    @Override
    public void onSuccess(RestruantByID restruant) {
        dialog.dismissDialog();
        this.restruantByID = restruant;
        if (StaticMethods.type.equals("W")) {
            if (restruant.getRes().get(0).getPickup().equals("1")) {
                binding.btnP.setVisibility(View.VISIBLE);
            }
            if (restruant.getRes().get(0).getCurbside().equals("1")) {
                binding.btnC.setVisibility(View.VISIBLE);
            }
            if (restruant.getRes().get(0).getDine_in().equals("1")) {
                binding.btnD.setVisibility(View.VISIBLE);
            }
        }

        binding.phone.setText(restruant.getRes().get(0).getPhone());
        //binding.hours.setText("Daily "+restruant.getRes().get(0).getFrom_date()+" - "+restruant.getRes().get(0).getTo_date());
        binding.address.setText(restruant.getRes().get(0).getAddress());
        lat = restruant.getRes().get(0).getLatitude();
        lng = restruant.getRes().get(0).getLongitude();
        name = restruant.getRes().get(0).getName();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(getActivity(), error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(getActivity(), R.string.noConnection);
    }

    @Override
    public void onApply(boolean status, RemoveFood<RemoveFoodOBJ> data) {

    }

    @Override
    public void onFood(FoodData data) {

        /*adapter = new MenuAdapter(getActivity(), data, this);
        binding.recFoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recFoods.setAdapter(adapter);*/
    }

    @Override
    public void onMenuFood(RestMenu data) {
        if (StaticMethods.type.equals("D")){
            adapterMenu = new NewMenuAdapter(getActivity(), data, StaticMethods.type , 1);
            binding.recFoods.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recFoods.setAdapter(adapterMenu);
        }else {
            adapterMenu = new NewMenuAdapter(getActivity(), data, StaticMethods.type , 0);
            binding.recFoods.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recFoods.setAdapter(adapterMenu);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(sydney).title(name));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17), 200, null);

    }

    private void openEditDialog(String termsS) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.image_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        ImageView close = dialogView.findViewById(R.id.close);

        final ImageView terms = dialogView.findViewById(R.id.txtTerms);
        Picasso.with(getActivity()).load(termsS).into(terms);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }

    @Override
    public void onClicked(String image) {
        Bundle bundle = new Bundle();
        bundle.putString("img", image);
        IntentUtilies.openActivityWithBundle(getActivity(), ImageDisplay.class, bundle);

        //openEditDialog(image);
    }

    public class MenuHandler {
        Context context;

        public MenuHandler(Context context) {
            this.context = context;
        }

        public void onPickUp(View v) {
            ((RestaurantDetails) getActivity()).openPickUp();
        }

        public void onDineIn(View v) {
            ((RestaurantDetails) getActivity()).openDineIn();
        }

        public void onCurbside(View v) {
            ((RestaurantDetails) getActivity()).openCurbside();
        }
    }
}