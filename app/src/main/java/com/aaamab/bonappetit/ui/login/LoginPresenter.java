package com.aaamab.bonappetit.ui.login;

import android.content.Context;

import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.LoginData;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

public class LoginPresenter {
    LoginInter login ;

    public LoginPresenter(LoginInter login) {
        this.login = login;
    }

    public void loginFun(Context context , String email , String pass){
        boolean network = StaticMethods.isConnectingToInternet(context);
        if(network){
            RequestBody body = null ;
            try{
                body = MainApiBody.loginBody(email , pass);
            }catch (Exception e){

            }
            MainApi.LoginApi(body, new ConnectionListener<DataObj<LoginData>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<LoginData>> connectionResponse) {
                    if (connectionResponse.data.data != null){
                        login.onSuccess(connectionResponse.data.data);
                    }else {
                        login.onFail(connectionResponse.data.getMsg());
                    }

                }

                @Override
                public void onFail(Throwable throwable) {
                    login.onFail(throwable.getMessage());
                }
            });
        }else {
            login.onConnection(true);
        }
    }
    public void asGuest(Context context ){
        boolean network = StaticMethods.isConnectingToInternet(context);
        if(network){
            RequestBody body = null ;
            try{
                body = MainApiBody.loginBody("aaamab257@gmail.com" , "pass");
            }catch (Exception e){

            }
            MainApi.ContinueAsGuest(body , LocaleManager.getLanguage(context), new ConnectionListener<DataObj<LoginData>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<LoginData>> connectionResponse) {
                    if (connectionResponse.data.data != null){
                        login.asGuest(connectionResponse.data.data);
                    }else {
                        login.onFail(connectionResponse.data.getMsg());
                    }

                }

                @Override
                public void onFail(Throwable throwable) {
                    login.onFail(throwable.getMessage());
                }
            });
        }else {
            login.onConnection(true);
        }
    }
}
