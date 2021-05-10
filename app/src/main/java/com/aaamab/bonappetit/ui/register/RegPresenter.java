package com.aaamab.bonappetit.ui.register;

import android.content.Context;

import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.LoginData;
import com.aaamab.bonappetit.data.RegisterData;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

public class RegPresenter {
    RegInter register ;

    public RegPresenter(RegInter register) {
        this.register = register;
    }
    public void registerFun(Context context , String email , String phone , String pass ,String name , String passCon , String address  , String gender){
        boolean network = StaticMethods.isConnectingToInternet(context);
        if(network){
            RequestBody body = null ;
            try{
                body = MainApiBody.registerBody(email , phone ,pass ,name ,passCon , gender,address);
            }catch (Exception e){

            }
            MainApi.RegisterApi(body, new ConnectionListener<DataObj<RegisterData>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<RegisterData>> connectionResponse) {
                    if (connectionResponse.data.data != null){
                        register.onSuccess(connectionResponse.data.data);
                    }else {
                        register.onFail(connectionResponse.data.getMsg());
                    }

                }

                @Override
                public void onFail(Throwable throwable) {
                    register.onFail(throwable.getMessage());
                }
            });
        }else {
            register.onConnection(true);
        }
    }
}
