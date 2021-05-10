package com.aaamab.bonappetit.ui.support;

import android.content.Context;

import com.aaamab.bonappetit.data.SupportArray;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;

public class SupportPresenter {
    SupportInterface supportInterface ;

    public SupportPresenter(SupportInterface supportInterface) {
        this.supportInterface = supportInterface;
    }

    public void getSupport(Context context ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if(net){
            MainApi.getSupport(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context),StaticMethods.userData.getApi_token(), new ConnectionListener<SupportArray>() {
                @Override
                public void onSuccess(ConnectionResponse<SupportArray> connectionResponse) {
                    if(connectionResponse.data.code == 200){
                        supportInterface.onSuccess(connectionResponse.data);
                    }else {
                        supportInterface.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    supportInterface.onFail(throwable.getMessage());
                }
            });
        }else {
            supportInterface.onConnection(true);
        }
    }
}
