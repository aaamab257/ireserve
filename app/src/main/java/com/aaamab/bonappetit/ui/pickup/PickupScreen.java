package com.aaamab.bonappetit.ui.pickup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.ChangePass;
import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.FoodData;
import com.aaamab.bonappetit.data.MakeBook;
import com.aaamab.bonappetit.data.MyOrdersArray;
import com.aaamab.bonappetit.data.NewMakeBook;
import com.aaamab.bonappetit.data.PaymentSummry;
import com.aaamab.bonappetit.data.RemoveFood;
import com.aaamab.bonappetit.data.RemoveFoodOBJ;
import com.aaamab.bonappetit.data.RestMenu;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.data.SeatingData;
import com.aaamab.bonappetit.databinding.ActivityPickupScreenBinding;
import com.aaamab.bonappetit.ui.adapter.NewMenuAdapter;
import com.aaamab.bonappetit.ui.adapter.PickupAdapter;
import com.aaamab.bonappetit.ui.dineinOrder.InterfaceDineIn;
import com.aaamab.bonappetit.ui.dineinOrder.PresenterDineIn;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.menus.MenuInter;
import com.aaamab.bonappetit.ui.menus.MenuPresenter;
import com.aaamab.bonappetit.ui.myOrder.MyOrderInter;
import com.aaamab.bonappetit.ui.myOrder.MyOrderPresenter;
import com.aaamab.bonappetit.ui.myOrder.MyOrderScreen;
import com.aaamab.bonappetit.ui.payment.PaymentScreen;
import com.aaamab.bonappetit.ui.paymentMethod.PaymentSummary;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtil;
import com.aaamab.bonappetit.utils.dialogs.DialogUtilResponse;
import com.aaamab.bonappetit.utils.network.MainApiBody;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.RequestBody;

public class PickupScreen extends AppCompatActivity implements MenuInter, MyOrderInter, InterfaceDineIn, DialogUtilResponse, PaymentSummary {
    PickupAdapter adapter;
    PickupHandler handler;
    ActivityPickupScreenBinding binding;

    CustomDialog dialog;
    int ResID;
    String type;
    int tt = 1;
    MenuPresenter presenter;
    MyOrderPresenter presenterMy;
    PresenterDineIn dineIn;
    String url = "";
    DialogUtil dialogUtil;
    String date = "", time = "", count = "1";
    int paymentType = 0;
    NewMenuAdapter adapterMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pickup_screen);
        handler = new PickupHandler(this);
        binding.setHandler(handler);
        dialog = new CustomDialog(this);
        presenter = new MenuPresenter(this);
        presenterMy = new MyOrderPresenter(this, this);
        //dialog.showDialog();
        dialogUtil = new DialogUtil(this);
        presenter.getMenu(this, StaticMethods.resID);
        presenter.getMenuData(this, StaticMethods.resID);
        dineIn = new PresenterDineIn(this, this);
        Bundle bundle = getIntent().getExtras();
        tt = bundle.getInt("type", 1);
        if (tt == 0) {
            binding.textView.setText(this.getString(R.string.dine_in));
            binding.btnSkip.setVisibility(View.VISIBLE);
            url = bundle.getString("url");
            date = bundle.getString("date");
            time = bundle.getString("time");
            count = bundle.getString("count");
        }
    }

    @Override
    public void onSuccess(RestruantByID restruant) {

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }

    @Override
    public void onMyOrder(MyOrdersArray array) {
        if (tt == 0) {
            if (array.getMyOrders().size() > 0) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "D");
                bundle.putInt("resId", ResID);
                StaticMethods.Date = date;
                StaticMethods.Time = time;
                StaticMethods.Count = count;
                IntentUtilies.openActivityWithBundle(PickupScreen.this, MyOrderScreen.class, bundle);
            } else {
                ToastUtil.showErrorToast(PickupScreen.this, R.string.add_food_txt);
            }
        } else {
            if (array.getMyOrders().size() > 0) {
                Bundle b = new Bundle();
                b.putString("type", "P");
                b.putInt("resId", ResID);
                IntentUtilies.openActivityWithBundle(PickupScreen.this, MyOrderScreen.class, b);
            } else {
                dialog.dismissDialog();
                ToastUtil.showErrorToast(PickupScreen.this, R.string.add_food_txt);
            }
        }


    }

    @Override
    public void onSuccess(MakeBook data) {
        if (tt == 0) {
            Bundle bundle = new Bundle();
            bundle.putString("url", data.data.getUrl());
            IntentUtilies.openActivityWithBundle(PickupScreen.this, PaymentScreen.class, bundle);
        } else {
            if (paymentType == 0) {
                Bundle bundle = new Bundle();
                bundle.putString("url", data.data.getUrl());
                IntentUtilies.openActivityWithBundle(PickupScreen.this, PaymentScreen.class, bundle);
            }
        }
    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(PickupScreen.this, R.string.add_food_txt);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(PickupScreen.this, R.string.noConnection);
    }

    @Override
    public void onSeatingType(SeatingData data) {

    }

    @Override
    public void onSuccessStatus(NewMakeBook data) {

    }

    @Override
    public void onDeleteOrder(DataObj<ChangePass> dataObj) {

    }

    @Override
    public void onApply(boolean status, RemoveFood<RemoveFoodOBJ> data) {

        /*if(status){
            IntentUtilies.openActivity(PickupScreen.this , MyOrderScreen.class);
        }*/
    }

    @Override
    public void onFood(FoodData data) {
        dialog.dismissDialog();
        ResID = data.getFa().get(0).getRestaurant_id();
        type = "P";
        /*adapter = new PickupAdapter(this, data , type);
        binding.recPickup.setLayoutManager(new LinearLayoutManager(this));
        binding.recPickup.setAdapter(adapter);*/
    }

    @Override
    public void onMenuFood(RestMenu data) {
        if (tt == 0){
            type = "D";
        }else {
            type = "P";
        }
        adapterMenu = new NewMenuAdapter(this, data, type , 0);
        binding.recPickup.setLayoutManager(new LinearLayoutManager(this));
        binding.recPickup.setAdapter(adapterMenu);
    }

    @Override
    public void selectedValueSingleChoice(int position) {

    }

    @Override
    public void selectedValueSingleChoice(int position, String arrayType) {
        /*if (arrayType.equals("payment")) {
            if (position == 0) {
                if (tt == 0){
                    paymentType = 0 ;
                    //presenter.applyOrder(PickupScreen.this , ResID , "D" , "visa" );
                    dineIn.makeBook(PickupScreen.this, ResID, date, time, count, "visa");
                }else {
                    paymentType = 0 ;
                    presenter.applyOrder(PickupScreen.this , ResID , "P" , "visa" );
                    //dineIn.makeBook(PickupScreen.this, ResID, date, time, count, "visa");
                }


            } else if (position == 1) {
                if (tt == 0) {
                    paymentType = 1 ;
                    //presenter.applyOrder(PickupScreen.this , ResID , "P" ,"cash"  );
                    dineIn.makeBook(PickupScreen.this, ResID, date, time, count, "cash");
                }else {
                    paymentType = 1 ;
                    ToastUtil.showSuccessToast(PickupScreen.this, "Order Added Successfully");
                    IntentUtilies.openActivityInNewStack(PickupScreen.this, MainScreen.class);
                }
            }
        }*/
    }

    @Override
    public void selectedMultiChoicelang(ArrayList<String> choices, ArrayList<String> postions, String arrayType) {

    }

    @Override
    public void onPayment(PaymentSummry data) {

    }

    public class PickupHandler {
        Context context;

        public PickupHandler(Context context) {
            this.context = context;
        }
        public void home(View v){
            IntentUtilies.openActivityInNewStack(PickupScreen.this, MainScreen.class);
        }
        public void apply(View v) {
            //dialog.showDialog();
            RequestBody body = null;
            try {
                body = MainApiBody.myOrderBody(type, ResID);
            } catch (Exception e) {

            }
            //presenterMy.myOrder(PickupScreen.this , body);
            if (tt == 0) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "D");
                bundle.putInt("resId", ResID);
                StaticMethods.Date = date;
                StaticMethods.Time = time;
                StaticMethods.Count = count;
                IntentUtilies.openActivityWithBundle(PickupScreen.this, MyOrderScreen.class, bundle);

            } else {
                /*
                * app:picker_displayDays="false"
                    app:picker_displayDaysOfMonth="false"
                    app:picker_displayMonth="false"
                    app:picker_displayYears="false"*/

                openTimeDialog();
            }
        }

        public void back(View v) {
            onBackPressed();
        }

        public void onSkip(View v) {
            StaticMethods.Date = date;
            StaticMethods.Time = time;
            StaticMethods.Count = count;
            dineIn.makeBook(PickupScreen.this, ResID, date, time, count, "cash");
            /*ArrayList<String> payment = new ArrayList<>();
            payment.add("Visa");
            payment.add("Cash");
            dialogUtil.showDialogSingleChooice(PickupScreen.this, "Select the Payment type", R.string.ok, payment, "payment");*/
            /*Bundle bundle = new Bundle();
            bundle.putString("url",url);
            IntentUtilies.openActivityWithBundle(PickupScreen.this, PaymentScreen.class , bundle);*/

        }
    }
    private void openTimeDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.time_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Button confirm = dialogView.findViewById(R.id.btn_confirm);
        final SingleDateAndTimePicker picker = dialogView.findViewById(R.id.single_day_picker);




        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = picker.getDate();
                DateFormat timeFormat = new SimpleDateFormat("hh:mm");
                String timeForm = timeFormat.format(date);
                Bundle b = new Bundle();
                b.putString("type", "P");
                b.putInt("resId", ResID);
                b.putString("time" , timeForm);
                IntentUtilies.openActivityWithBundle(PickupScreen.this, MyOrderScreen.class, b);
                alertDialog.dismiss();
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }
}