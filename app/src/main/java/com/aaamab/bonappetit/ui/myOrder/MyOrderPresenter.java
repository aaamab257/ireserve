package com.aaamab.bonappetit.ui.myOrder;

import android.content.Context;
import android.util.Log;

import com.aaamab.bonappetit.data.ChangePass;
import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.MyOrdersArray;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.ui.menus.MenuInter;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

import static android.content.ContentValues.TAG;

public class MyOrderPresenter {
    MyOrderInter myOrder ;
    MenuInter menu ;

    public MyOrderPresenter(MyOrderInter myOrder, MenuInter menu) {
        this.myOrder = myOrder;
        this.menu = menu;
    }

    public void myOrder(Context context ,RequestBody body ){

        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){

            MainApi.myOrder(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context),body, new ConnectionListener<MyOrdersArray>() {
                @Override
                public void onSuccess(ConnectionResponse<MyOrdersArray> connectionResponse) {
                    StaticMethods.printJson("j" ,connectionResponse.data );
                    if (connectionResponse.data.getMyOrders().size() > 0 ){
                        myOrder.onMyOrder(connectionResponse.data);
                        Log.e(TAG, "onSuccess: "+connectionResponse.data.getMyOrders().size() );
                    }else {
                        myOrder.onFail("No foods");
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    Log.e(TAG, "onFail: "+throwable.getMessage() );
                    myOrder.onFail("Server error , try again");
                }
            });
        }else {
            myOrder.onConnection(true);
        }
    }
    public void deleteOrder(Context context , int id , String type  ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            RequestBody body = null ;
            try {
                body = MainApiBody.deleteAllOrder(type,id);
            }catch (Exception e){

            }
            MainApi.deleteOrder(StaticMethods.userData.getApi_token() ,body, new ConnectionListener<DataObj<ChangePass>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<ChangePass>> connectionResponse) {
                    if (connectionResponse.data.getData().isStatus()){
                        myOrder.onDeleteOrder(connectionResponse.data);
                    }else {
                        myOrder.onFail(connectionResponse.data.getMsg());
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    myOrder.onFail("Server error , try again");
                }
            });
        }else {
            myOrder.onConnection(true);
        }
    }
}
