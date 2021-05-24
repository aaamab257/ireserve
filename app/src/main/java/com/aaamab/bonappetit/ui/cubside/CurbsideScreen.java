package com.aaamab.bonappetit.ui.cubside;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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
import com.aaamab.bonappetit.databinding.ActivityCurbsideScreenBinding;
import com.aaamab.bonappetit.ui.adapter.NewMenuAdapter;
import com.aaamab.bonappetit.ui.adapter.PickupAdapter;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.menus.MenuInter;
import com.aaamab.bonappetit.ui.menus.MenuPresenter;
import com.aaamab.bonappetit.ui.myOrder.MyOrderInter;
import com.aaamab.bonappetit.ui.myOrder.MyOrderPresenter;
import com.aaamab.bonappetit.ui.myOrder.MyOrderScreen;
import com.aaamab.bonappetit.ui.pickup.PickupScreen;
import com.aaamab.bonappetit.ui.resDetails.RestaurantDetails;
import com.aaamab.bonappetit.utils.CustomDialog;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.MainApiBody;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.RequestBody;

public class CurbsideScreen extends AppCompatActivity implements MenuInter, MyOrderInter {
    ActivityCurbsideScreenBinding binding ;
    CurbsideHandler handler ;
    PickupAdapter adapter ;
    int ResID ;
    String type;
    MenuPresenter presenter ;
    MyOrderPresenter presenterMy;
    CustomDialog dialog ;
    NewMenuAdapter adapterMenu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_curbside_screen);
        handler = new CurbsideHandler(this);
        binding.setHandler(handler);
        dialog = new CustomDialog(this);
        presenter = new MenuPresenter(this);
        presenterMy = new MyOrderPresenter(this , this);
        //dialog.showDialog();
        presenter.getMenu(this , StaticMethods.resID);
        presenter.getMenuData(this,StaticMethods.resID );
        /*adapter = new PickupAdapter(this);
        binding.recCurbside.setLayoutManager(new LinearLayoutManager(this));
        binding.recCurbside.setAdapter(adapter);*/
    }

    @Override
    public void onSuccess(RestruantByID restruant) {

    }

    @Override
    public void onMyOrder(MyOrdersArray array) {
        Log.e("TAG", "onMyOrder: "+array.getMyOrders().size() );
        if(array.getMyOrders().size() > 0){
            dialog.dismissDialog();
            /*Bundle b = new Bundle();
            b.putString("type","C");
            IntentUtilies.openActivityWithBundle(CurbsideScreen.this , MyOrderScreen.class,b);*/
            Bundle b = new Bundle();
            b.putString("type", "C");
            b.putInt("resId" , StaticMethods.resID);
            IntentUtilies.openActivityWithBundle(CurbsideScreen.this, MyOrderScreen.class, b);
        }else {
            dialog.dismissDialog();
            ToastUtil.showErrorToast(CurbsideScreen.this , R.string.add_food_txt);
        }
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }
    @Override
    public void onFail(String error) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(CurbsideScreen.this,R.string.add_food_txt);
    }

    @Override
    public void onConnection(boolean isConnected) {
        dialog.dismissDialog();
        ToastUtil.showErrorToast(CurbsideScreen.this,R.string.noConnection);
    }

    @Override
    public void onDeleteOrder(DataObj<ChangePass> dataObj) {

    }

    @Override
    public void onApply(boolean status, RemoveFood<RemoveFoodOBJ> data) {
        /*if(status){
            IntentUtilies.openActivity(CurbsideScreen.this , MyOrderScreen.class);
        }*/
    }

    @Override
    public void onFood(FoodData data) {
        dialog.dismissDialog();

    }

    @Override
    public void onMenuFood(RestMenu data) {
       /* ResID = data.data.getFa().get(0).getRestaurant_id() ;*/
        type = "C" ;
        adapterMenu = new NewMenuAdapter(this,data,"C" , 0);
        binding.recCurbside.setLayoutManager(new LinearLayoutManager(this));
        binding.recCurbside.setAdapter(adapterMenu);
    }

    public class CurbsideHandler{
        Context context ;

        public CurbsideHandler(Context context) {
            this.context = context;
        }
        public void home(View v){
            IntentUtilies.openActivityInNewStack(CurbsideScreen.this, MainScreen.class);
        }
        public void back(View v){
            onBackPressed();
        }
        public void apply(View v){
            //dialog.showDialog();
            RequestBody body = null ;
            try {
                body = MainApiBody.myOrderBody(type ,ResID);
            }catch (Exception e){

            }
            openTimeDialog();
            //presenterMy.myOrder(CurbsideScreen.this , body);
            //presenter.applyOrder(CurbsideScreen.this , ResID , "C" );

            /*IntentUtilies.openActivity(CurbsideScreen.this , MyOrderScreen.class);*/
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
                b.putString("type", "C");
                b.putInt("resId" , StaticMethods.resID);
                b.putString("time" , timeForm);
                IntentUtilies.openActivityWithBundle(CurbsideScreen.this, MyOrderScreen.class, b);
                alertDialog.dismiss();
            }
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
    }
}