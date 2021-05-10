package com.aaamab.bonappetit.ui.offers;

import android.content.Context;

import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.LoginData;
import com.aaamab.bonappetit.data.OffersData;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

public class OfferPresenter {
    OffersInter offer ;

    public OfferPresenter(OffersInter offer) {
        this.offer = offer;
    }
    public void getOffers(String token , Context context){
        boolean network = StaticMethods.isConnectingToInternet(context);
        if(network){

            MainApi.getOffers(token , LocaleManager.getLanguage(context), new ConnectionListener<OffersData>() {
                @Override
                public void onSuccess(ConnectionResponse<OffersData> connectionResponse) {
                    if(connectionResponse.data.getCode() == 200){
                        offer.onSuccess(connectionResponse.data);
                    }else {
                        offer.onFail(connectionResponse.data.msg);
                    }

                }

                @Override
                public void onFail(Throwable throwable) {
                    offer.onFail("Server Error");
                }
            });
        }else {
            offer.onConnection(true);
        }
    }
}
