package com.aaamab.bonappetit.ui.myOrder;

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
import com.aaamab.bonappetit.data.MyOrdersArray;
import com.aaamab.bonappetit.data.RemoveFood;
import com.aaamab.bonappetit.data.RemoveFoodOBJ;
import com.aaamab.bonappetit.data.RestMenu;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.databinding.ActivityMyOrderScreenBinding;
import com.aaamab.bonappetit.ui.adapter.MyOrderAdapter;
import com.aaamab.bonappetit.ui.adapter.PickupAdapter;
import com.aaamab.bonappetit.ui.cubside.CurbsideScreen;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.menus.MenuInter;
import com.aaamab.bonappetit.ui.menus.MenuPresenter;
import com.aaamab.bonappetit.ui.paymentMethod.PaymentMethodScreen;
import com.aaamab.bonappetit.ui.pickup.PickupScreen;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.MainApiBody;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.RequestBody;

public class MyOrderScreen extends AppCompatActivity implements MenuInter, MyOrderInter {
    ActivityMyOrderScreenBinding binding;
    MyOrderHandler handler;
    MyOrderAdapter adapter;
    MenuPresenter menuPresenter;
    MyOrderPresenter presenter;
    CustomDialog dialog;
    int id;
    String type = "";
    int Res = 0 ;
    public boolean isFood = true ;
    String time = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order_screen);
        handler = new MyOrderHandler(this);
        binding.setHandler(handler);
        dialog = new CustomDialog(this);
        menuPresenter = new MenuPresenter(this);
        presenter = new MyOrderPresenter(this, this);
        //dialog.showDialog();


        Bundle getB = getIntent().getExtras();
        type = getB.getString("type");
        Res = getB.getInt("resId" , 0);
        time = getB .getString("time");
        RequestBody body = null ;
        try {
            body = MainApiBody.myOrderBody(type ,Res);
        }catch (Exception e){

        }
        presenter.myOrder(this , body);
        /**/
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
        dialog.dismissDialog();
        id = array.getMyOrders().get(0).getFood().getRestaurant_id();

        isFood = true ;
        adapter = new MyOrderAdapter(this, array ,type);
        binding.recMyOrder.setLayoutManager(new LinearLayoutManager(this));
        binding.recMyOrder.setAdapter(adapter);
    }

    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(MyOrderScreen.this, error);
        isFood = false ;
    }

    @Override
    public void onConnection(boolean isConnected) {

    }

    @Override
    public void onDeleteOrder(DataObj<ChangePass> dataObj) {
        dialog.dismissDialog();
        ToastUtil.showSuccessToast(MyOrderScreen.this, R.string.order_de);
        IntentUtilies.openActivityInNewStack(MyOrderScreen.this, MainScreen.class);
    }

    @Override
    public void onApply(boolean status, RemoveFood<RemoveFoodOBJ> data) {
        if (status) {
            dialog.dismissDialog();
            ToastUtil.showSuccessToast(MyOrderScreen.this, R.string.order_added);
        }
    }

    @Override
    public void onFood(FoodData data) {

    }

    @Override
    public void onMenuFood(RestMenu data) {

    }

    public class MyOrderHandler {
        Context context;

        public MyOrderHandler(Context context) {
            this.context = context;
        }

        public void back(View view) {
            onBackPressed();
        }
        public void home(View v){
            IntentUtilies.openActivityInNewStack(MyOrderScreen.this, MainScreen.class);
        }
        public void apply(View v) {
            if(isFood){
                if(adapter.getItemCount() > 0){
                   // dialog.showDialog();
                    /*Bundle b = new Bundle();
                    b.putString("type" , type);
                    b.putInt("resId" , Res);
                    b.putString("time" , time);
                    IntentUtilies.openActivityWithBundle(MyOrderScreen.this, PaymentMethodScreen.class,b);*/
                    if (type.equals("D")){
                        Bundle b = new Bundle();
                        b.putString("type" , type);
                        b.putInt("resId" , Res);
                        b.putString("time" , time);
                        IntentUtilies.openActivityWithBundle(MyOrderScreen.this, PaymentMethodScreen.class,b);
                    }else {
                        openTimeDialog();
                    }
                }else {
                    dialog.dismissDialog();
                    ToastUtil.showErrorToast(MyOrderScreen.this, R.string.no_food_add);
                    isFood = false ;
                }

            }else {
                dialog.dismissDialog();
                ToastUtil.showErrorToast(MyOrderScreen.this, R.string.no_food_add);
            }

        }

        public void deleteAll(View v) {
            if(isFood){
                if(adapter.getItemCount() > 0){
                    //dialog.showDialog();
                    presenter.deleteOrder(MyOrderScreen.this , Res ,type );
                    adapter.clear();
                }else {
                    dialog.dismissDialog();
                    ToastUtil.showErrorToast(MyOrderScreen.this, R.string.no_food_delete);
                    isFood = false ;
                }
            }else {
                dialog.dismissDialog();
                ToastUtil.showErrorToast(MyOrderScreen.this, R.string.no_food_delete);
            }

        }
    }

    private void openTimeDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.time_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Button confirm = dialogView.findViewById(R.id.btn_confirm); //btn_Skip
        Button skip = dialogView.findViewById(R.id.btn_Skip); //btn_Skip
        final SingleDateAndTimePicker picker = dialogView.findViewById(R.id.single_day_picker);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = picker.getDate();
                DateFormat timeFormat = new SimpleDateFormat("hh:mm");
                String timeForm = timeFormat.format(date);
                Bundle b = new Bundle();
                b.putString("type", type);
                b.putInt("resId", Res);
                b.putString("time" , timeForm);
                IntentUtilies.openActivityWithBundle(MyOrderScreen.this, PaymentMethodScreen.class, b);
                alertDialog.dismiss();
            }
        });



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = picker.getDate();
                DateFormat timeFormat = new SimpleDateFormat("hh:mm");
                String timeForm = timeFormat.format(date);
                Bundle b = new Bundle();
                b.putString("type", type);
                b.putInt("resId", Res);
                b.putString("time" , timeForm);
                IntentUtilies.openActivityWithBundle(MyOrderScreen.this, PaymentMethodScreen.class, b);
                alertDialog.dismiss();
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }
}