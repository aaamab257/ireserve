package com.aaamab.bonappetit.ui.paymentMore;

import android.content.Context;

import com.aaamab.bonappetit.data.PaymentMoreData;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;

public class Presenter {
    PaymentMoreInter paymentMoreInter ;

    public Presenter(PaymentMoreInter paymentMoreInter) {
        this.paymentMoreInter = paymentMoreInter;
    }


    public void getPayments(Context context ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            MainApi.PayData(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), new ConnectionListener<PaymentMoreData>() {
                @Override
                public void onSuccess(ConnectionResponse<PaymentMoreData> connectionResponse) {
                    if (connectionResponse.data.code == 200){
                        paymentMoreInter.onSuccess(connectionResponse.data);

                    }else {
                        paymentMoreInter.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    paymentMoreInter.onFail(throwable.getMessage());
                }
            });
        }else {
            paymentMoreInter.onConnection(true);
        }
    }
}
