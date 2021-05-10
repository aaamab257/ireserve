package com.aaamab.bonappetit.ui.resDetails;

import android.content.Context;

import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.MakeFavorites;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

public class ResPresenter {
    ResByIdInter res ;

    public ResPresenter(ResByIdInter res) {
        this.res = res;
    }

    public void getDetails(Context context , int resID ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            RequestBody body = null ;
            try {
                body = MainApiBody.res(resID);
            }catch (Exception e){

            }
            MainApi.getResById(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<RestruantByID>() {
                @Override
                public void onSuccess(ConnectionResponse<RestruantByID> connectionResponse) {
                    if (connectionResponse.data.getRes().size() > 0 ){
                        res.onSuccess(connectionResponse.data);
                    }else {
                        res.onFail("No Data To Show");
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    res.onFail("Server error , try again");
                }
            });
        }else {
            res.onConnection(true);
        }
    }
    public void makeFav(Context context , int resID ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            RequestBody body = null ;
            try {
                body = MainApiBody.res(resID);
            }catch (Exception e){

            }
            MainApi.postFav(StaticMethods.userData.getApi_token(), body, new ConnectionListener<DataObj<MakeFavorites>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<MakeFavorites>> connectionResponse) {
                    if (connectionResponse.data.getCode() == 200 ){
                        res.onMakeFav(connectionResponse.data);
                    }else {
                        res.onFail("Error");
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    res.onFail("Server error , try again");
                }
            });
        }else {
            res.onConnection(true);
        }
    }
    public void removeFav(Context context , int resID ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            RequestBody body = null ;
            try {
                body = MainApiBody.res(resID);
            }catch (Exception e){

            }
            MainApi.removeFav(StaticMethods.userData.getApi_token(), body, new ConnectionListener<DataObj<MakeFavorites>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<MakeFavorites>> connectionResponse) {
                    if (connectionResponse.data.data.isStatus()){
                        res.onRemoveFav(connectionResponse.data);
                    }else {
                        res.onFail("Error");
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    res.onFail("Server error , try again");
                }
            });
        }else {
            res.onConnection(true);
        }
    }
}
