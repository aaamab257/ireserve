package com.aaamab.bonappetit.ui.dineinOrder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.MakeBook;
import com.aaamab.bonappetit.data.MakeBookings;
import com.aaamab.bonappetit.data.MakePaymentOBJ;
import com.aaamab.bonappetit.data.NewMakeBook;
import com.aaamab.bonappetit.data.PaymentSummry;
import com.aaamab.bonappetit.data.SeatingData;
import com.aaamab.bonappetit.databinding.ActivityDineInOrderScreenBinding;
import com.aaamab.bonappetit.ui.adapter.TableSeatCountAdapter;
import com.aaamab.bonappetit.ui.payment.PaymentScreen;
import com.aaamab.bonappetit.ui.paymentMethod.PaymentSummary;
import com.aaamab.bonappetit.ui.pickup.PickupScreen;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtilResponse;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DineInOrderScreen extends AppCompatActivity implements onCountSelected, InterfaceDineIn, DialogUtilResponse, PaymentSummary {
    ActivityDineInOrderScreenBinding binding;
    DineInOrderHandler handler;
    TableSeatCountAdapter adapter;
    PresenterDineIn presenterDineIn;
    String count = "1";
    CustomDialog dialog;
    DialogUtil dialogUtil;
    int seatingType = 0;
    ArrayList<String> name;
    ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dine_in_order_screen);

        handler = new DineInOrderHandler(this);
        presenterDineIn = new PresenterDineIn(this, this);
        binding.setHandler(handler);
        dialogUtil = new DialogUtil(this);
        adapter = new TableSeatCountAdapter(this, this);
        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recSeats.setLayoutManager(layoutManager);
        binding.recSeats.setAdapter(adapter);
        dialog = new CustomDialog(this);
        presenterDineIn.getSeating(this, StaticMethods.resID);
        getBu();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }

    private void getBu() {
        Bundle bu = getIntent().getExtras();
        String name = bu.getString("name");
        String image = bu.getString("image");

        binding.resName.setText(name);
        Picasso.with(DineInOrderScreen.this).load(image).into(binding.resImage);
    }

    @Override
    public void onSelected(String count) {
        this.count = count;
        //ToastUtil.showSuccessToast(DineInOrderScreen.this,count+" Person");
    }

    @Override
    public void onSuccess(MakeBook data) {

        /*Bundle bundle = new Bundle();
        bundle.putString("url",data.data.getUrl());
        IntentUtilies.openActivityWithBundle(DineInOrderScreen.this, PaymentScreen.class , bundle);*/
    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(DineInOrderScreen.this, R.string.error);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(DineInOrderScreen.this, R.string.noConnection);
    }

    @Override
    public void onSeatingType(SeatingData data) {
        name = new ArrayList<>();
        ids = new ArrayList<>();
        for (int i = 0; i < data.data.size(); i++) {
            name.add(data.data.get(i).getName());
            ids.add(data.data.get(i).getId());
        }
    }

    @Override
    public void onSuccessStatus(NewMakeBook data) {
        dialog.dismissDialog();

        if (data.data.isStatus()) {
            Bundle bundle = new Bundle();
            Date date = binding.singleDayPicker.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String strDate = dateFormat.format(date);
            DateFormat timeFormat = new SimpleDateFormat("hh:mm");
            String timeForm = timeFormat.format(date);
            bundle.putInt("type", 0);
            bundle.putString("date", strDate);
            bundle.putString("time", timeForm);
            bundle.putString("count", count);
            //bundle.putString("url" , data.data.getUrl());
            IntentUtilies.openActivityWithBundle(this, PickupScreen.class, bundle);
        } else {
            ToastUtil.showErrorToast(DineInOrderScreen.this, R.string.no_places);
        }
    }

    @Override
    public void selectedValueSingleChoice(int position) {

    }

    @Override
    public void selectedValueSingleChoice(int position, String arrayType) {
        if (arrayType.equals("seating")) {
            binding.spinner.setText(name.get(position));
            seatingType = ids.get(position);
        }
    }

    @Override
    public void selectedMultiChoicelang(ArrayList<String> choices, ArrayList<String> postions, String arrayType) {

    }

    @Override
    public void onPayment(PaymentSummry data) {

    }

    public class DineInOrderHandler {
        Context context;

        public DineInOrderHandler(Context context) {
            this.context = context;
        }

        public void onBack(View v) {
            onBackPressed();
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        public void onApply(View v) {
            //dialog.showDialog();
            Date date = binding.singleDayPicker.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String strDate = dateFormat.format(date);
            DateFormat timeFormat = new SimpleDateFormat("hh:mm");
            String timeForm = timeFormat.format(date);
            presenterDineIn.makeBookStatus(DineInOrderScreen.this, StaticMethods.resID, strDate, timeForm, count, seatingType);


        }

        public void seating(View v) {
            if (name != null) {
                dialogUtil.showDialogSingleChooice(DineInOrderScreen.this, getString(R.string.seat_type), R.string.ok, name, "seating");
            }

        }
    }
}