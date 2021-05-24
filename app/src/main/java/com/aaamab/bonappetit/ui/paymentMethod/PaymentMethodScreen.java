package com.aaamab.bonappetit.ui.paymentMethod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FoodData;
import com.aaamab.bonappetit.data.MakeBook;
import com.aaamab.bonappetit.data.MakeBookings;
import com.aaamab.bonappetit.data.MakePaymentOBJ;
import com.aaamab.bonappetit.data.NewMakeBook;
import com.aaamab.bonappetit.data.PaymentSummry;
import com.aaamab.bonappetit.data.RemoveFood;
import com.aaamab.bonappetit.data.RemoveFoodOBJ;
import com.aaamab.bonappetit.data.RestMenu;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.data.SeatingData;
import com.aaamab.bonappetit.databinding.ActivityPaymentMethodScreenBinding;
import com.aaamab.bonappetit.databinding.ActivityPaymentScreenBinding;
import com.aaamab.bonappetit.ui.dineinOrder.InterfaceDineIn;
import com.aaamab.bonappetit.ui.dineinOrder.PresenterDineIn;
import com.aaamab.bonappetit.ui.main.MainScreen;
import com.aaamab.bonappetit.ui.menus.MenuInter;
import com.aaamab.bonappetit.ui.menus.MenuPresenter;
import com.aaamab.bonappetit.ui.myOrder.MyOrderScreen;
import com.aaamab.bonappetit.ui.payment.PaymentScreen;
import com.aaamab.bonappetit.ui.pickup.PickupScreen;
import com.aaamab.bonappetit.utils.IntentUtilies;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

public class PaymentMethodScreen extends AppCompatActivity implements MenuInter , InterfaceDineIn ,PaymentSummary {
    PaymentHandler handler ;
    ActivityPaymentMethodScreenBinding binding ;
    String pay = "" ,time = "" , type = "";
    MenuPresenter menuPresenter;
    PresenterDineIn dineIn , dine;
    int Res = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_payment_method_screen);
        handler = new PaymentHandler(this);
        binding.setHandler(handler);
        menuPresenter = new MenuPresenter(this);
        dineIn = new PresenterDineIn(this , this);

        Bundle getB = getIntent().getExtras();
        type = getB.getString("type");
        Res = getB.getInt("resId" , 0);
        time = getB.getString("time");
        RequestBody body = null ;
        try {
            body = MainApiBody.getPaymentSummary(Res,type,"");
        }catch (Exception e){

        }

        dineIn.getSummary(this,body);

        /*if (binding.checkCash.isChecked()){
            binding.checkKent.setChecked(false);
            pay = "cash";
        }else if (binding.checkCash.isChecked()){
            binding.checkKent.setChecked(true);
            pay = "kent";
        }
        if (binding.checkKent.isChecked()){
            binding.checkCash.setChecked(false);
            pay = "kent";
        }else {
            binding.checkCash.setChecked(true);
            pay = "cash";
        }*/
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.onAttach(base));
    }

    @Override
    public void onSuccess(RestruantByID restruant) {
        /*if (type.equals("D")){
            if (pay.equals("cash")){

            }
        }*/
    }

    @Override
    public void onSuccess(MakeBook data) {
        Bundle bundle = new Bundle();
        bundle.putString("url",data.data.getUrl());
        IntentUtilies.openActivityWithBundle(PaymentMethodScreen.this, PaymentScreen.class , bundle);
    }

    @Override
    public void onFail(String error) {

    }

    @Override
    public void onConnection(boolean isConnected) {

    }

    @Override
    public void onSeatingType(SeatingData data) {

    }

    @Override
    public void onSuccessStatus(NewMakeBook data) {

    }

    @Override
    public void onApply(boolean status , RemoveFood<RemoveFoodOBJ> data) {
        if (!pay.equals("cash")){
            Bundle bundle = new Bundle();
            bundle.putString("url",data.da.getUrl());
            IntentUtilies.openActivityWithBundle(PaymentMethodScreen.this, PaymentScreen.class , bundle);
        }else {
            ToastUtil.showSuccessToast(PaymentMethodScreen.this, R.string.order_added);
            IntentUtilies.openActivityInNewStack(PaymentMethodScreen.this, MainScreen.class);
        }
    }

    @Override
    public void onFood(FoodData data) {

    }

    @Override
    public void onMenuFood(RestMenu data) {

    }

    @Override
    public void onPayment(PaymentSummry data) {
        binding.txtTotalAmount.setText(data.data.getTotal_amount() + " KD");
        binding.txtSubTotal.setText(data.data.getSubtotal() + " KD");
        binding.txtServiceCharge.setText(data.data.getServices_charge() + " KD");

    }

    public class PaymentHandler{
        Context context ;

        public PaymentHandler(Context context) {
            this.context = context;
        }

        public void onBack (View v){
            onBackPressed();
        }
        public void home(View v){
            IntentUtilies.openActivityInNewStack(PaymentMethodScreen.this, MainScreen.class);
        }
        public void onProcess(View v){
            if (type.equals("D")){
                if (!pay.isEmpty()){
                    dineIn.makeBook(PaymentMethodScreen.this , Res, StaticMethods.Date , StaticMethods.Time,StaticMethods.Count,pay);
                }else {
                    ToastUtil.showErrorToast(PaymentMethodScreen.this, R.string.select_payment);
                }
            }else if (type.equals("P")) {
                if (pay.isEmpty()){
                    ToastUtil.showErrorToast(PaymentMethodScreen.this, R.string.select_payment);
                }else {
                    Log.e("TAG", "onProcess: "+"here" );
                    Log.e("TAG", "onProcess: id "+Res );
                    Log.e("TAG", "onProcess: type "+type );
                    Log.e("TAG", "onProcess: pay "+pay );
                    menuPresenter.applyOrder(PaymentMethodScreen.this, Res, type , pay ,time );
                }
            }else if (type.equals("C")){
                if (pay.isEmpty()){
                    ToastUtil.showErrorToast(PaymentMethodScreen.this, R.string.select_payment);
                }else {
                    Log.e("TAG", "onProcess: "+"here" );
                    Log.e("TAG", "onProcess: id "+Res );
                    Log.e("TAG", "onProcess: type "+type );
                    Log.e("TAG", "onProcess: pay "+pay );
                    menuPresenter.applyOrder(PaymentMethodScreen.this, Res, type , pay,time);
                }
            }


        }
        public void onCash(View v){
            if (binding.checkKent.isChecked()){
                binding.checkKent.setChecked(false);
                binding.checkCash.setChecked(true);
                pay = "cash";
            }else {
                binding.checkCash.setChecked(true);
                pay = "cash";
            }

        }
        public void onKent(View v){
            if (binding.checkCash.isChecked()){
                binding.checkKent.setChecked(true);
                binding.checkCash.setChecked(false);
                pay = "visa";
            }else {
                binding.checkKent.setChecked(true);
                pay = "visa";
            }
        }
    }
}