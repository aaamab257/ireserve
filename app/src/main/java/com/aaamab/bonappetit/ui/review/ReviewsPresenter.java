package com.aaamab.bonappetit.ui.review;

import android.content.Context;

import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.MakeReview;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

public class ReviewsPresenter {
    ReviewsInter re ;

    public ReviewsPresenter(ReviewsInter re) {
        this.re = re;
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
                        re.onSuccess(connectionResponse.data);
                    }else {
                        re.onFail("No Data To Show");
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    re.onFail("Server error , try again");
                }
            });
        }else {
            re.onConnection(true);
        }
    }
    public void postReview(Context context , String rev ,int resID , float rate){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            RequestBody body = null ;
            try {
                body = MainApiBody.postReview(rev,resID , rate);
            }catch (Exception e){

            }
            MainApi.postReview(StaticMethods.userData.getApi_token(), body, new ConnectionListener<DataObj<MakeReview>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<MakeReview>> connectionResponse) {
                    if (connectionResponse.data.getCode() == 200 ){
                        re.onPostReview(connectionResponse.data);
                    }else {
                        re.onFail("No Data To Show : " +connectionResponse.data.getCode());
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    re.onFail("Server error , try again");
                }
            });
        }else {
            re.onConnection(true);
        }
    }
}
