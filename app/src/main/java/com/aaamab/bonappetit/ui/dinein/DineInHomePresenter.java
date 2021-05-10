package com.aaamab.bonappetit.ui.dinein;

import android.content.Context;
import android.util.Log;

import com.aaamab.bonappetit.data.Rating;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.Upcomming;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

import static android.content.ContentValues.TAG;

public class DineInHomePresenter {
    DineInHomeInter dineIn ;

    public DineInHomePresenter(DineInHomeInter dineIn) {
        this.dineIn = dineIn;
    }


    public void getDineIn(Context context ,String body ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        String token = StaticMethods.userData.getApi_token() ;
        if(net){
            RequestBody body1 = null;
            try {
                Log.e(TAG, "getDineIn: "+body );
                body1 = MainApiBody.homeBody(body);
            }catch (Exception e){

            }
            if(token != null){
                MainApi.getHome(token, LocaleManager.getLanguage(context), body1, new ConnectionListener<RestArray>() {
                    @Override
                    public void onSuccess(ConnectionResponse<RestArray> connectionResponse) {
                        if(connectionResponse.data.getCode() == 200){
                            dineIn.onSuccess(connectionResponse.data);

                        }else {
                            dineIn.onFail(connectionResponse.data.getMsg());
                            Log.e(TAG, "getDineIn: "+connectionResponse.data.getMsg() );
                        }
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        dineIn.onFail("Server Error");
                    }
                });
            }else {
                dineIn.onFail("Error");
            }

        }else {

        }
    }
    public void getVip(Context context ,String body ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        String token = StaticMethods.userData.getApi_token() ;
        if(net){
            RequestBody body1 = null;
            try {
                Log.e(TAG, "getDineIn: "+body );
                body1 = MainApiBody.homeBody(body);
            }catch (Exception e){

            }
            if(token != null){
                MainApi.getRes(token, LocaleManager.getLanguage(context), body1, new ConnectionListener<RestArray>() {
                    @Override
                    public void onSuccess(ConnectionResponse<RestArray> connectionResponse) {
                        if(connectionResponse.data.getCode() == 200){
                            dineIn.onSuccess(connectionResponse.data);
                        }else {
                            dineIn.onFail(connectionResponse.data.getMsg());
                            Log.e(TAG, "getDineIn: "+connectionResponse.data.getMsg() );
                        }
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        dineIn.onFail("Server Error");
                    }
                });
            }else {
                dineIn.onFail("Error");
            }

        }else {
            dineIn.onConnection(true);
        }
    }

    public void getUp(Context context ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if(net){
            MainApi.upcoming(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), new ConnectionListener<Upcomming>() {
                @Override
                public void onSuccess(ConnectionResponse<Upcomming> connectionResponse) {
                    if(connectionResponse.data.code == 200){
                        dineIn.onUpComing(connectionResponse.data);
                    }else {
                        dineIn.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    dineIn.onFail(throwable.getMessage());
                }
            });
        }else {
            dineIn.onConnection(true);
        }
    }

    public void isRated(Context context ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if(net){
            MainApi.isRated(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), new ConnectionListener<Rating>() {
                @Override
                public void onSuccess(ConnectionResponse<Rating> connectionResponse) {
                    if(connectionResponse.data.code == 200){
                        dineIn.isRated(connectionResponse.data.data.status);
                    }else {
                        dineIn.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    dineIn.onFail(throwable.getMessage());
                }
            });
        }else {
            dineIn.onConnection(true);
        }
    }
    public void cancel(final Context context ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if(net){
            MainApi.cancelRate(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), new ConnectionListener<Rating>() {
                @Override
                public void onSuccess(ConnectionResponse<Rating> connectionResponse) {
                    if(connectionResponse.data.code == 200){
                        ToastUtil.showSuccessToast(context,"Rate canceled");
                    }else {
                        dineIn.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    dineIn.onFail(throwable.getMessage());
                }
            });
        }else {
            dineIn.onConnection(true);
        }
    }

    public void rate(final Context context , float rate){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            RequestBody body = null ;
            try {
                body = MainApiBody.rateBody(rate);
            }catch (Exception e){

            }
            MainApi.rate(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<Rating>() {
                @Override
                public void onSuccess(ConnectionResponse<Rating> connectionResponse) {
                    if (connectionResponse.data.data.isStatus()){
                        ToastUtil.showSuccessToast(context,"Rated successfully");
                    }else {
                        ToastUtil.showErrorToast(context,connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    ToastUtil.showErrorToast(context,"Error , please try again");
                }
            });
        }else {
            dineIn.onConnection(true);
        }
    }


}
