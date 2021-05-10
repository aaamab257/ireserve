package com.aaamab.bonappetit.ui.myOrders;

import android.content.Context;

import com.aaamab.bonappetit.data.AllMyOrders;
import com.aaamab.bonappetit.ui.myOrder.MyOrderInter;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;

public class MyOrdersPresenter {
    MyOrdersInter myOrderInter ;

    public MyOrdersPresenter(MyOrdersInter myOrderInter) {
        this.myOrderInter = myOrderInter;
    }

    public void getMyOrders(Context context ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if(net){
            MainApi.getAllMyOrders(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), new ConnectionListener<AllMyOrders>() {
                @Override
                public void onSuccess(ConnectionResponse<AllMyOrders> connectionResponse) {
                    if (connectionResponse.data.code == 200){
                        myOrderInter.onSuccess(connectionResponse.data);
                    }else {
                        myOrderInter.onFail(connectionResponse.data.msg);
                    }

                }

                @Override
                public void onFail(Throwable throwable) {
                    myOrderInter.onFail(throwable.getMessage());
                }
            });
        }else {
            myOrderInter.onConnection(true);
        }
    }
}
