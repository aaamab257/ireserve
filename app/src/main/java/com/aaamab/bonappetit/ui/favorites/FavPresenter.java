package com.aaamab.bonappetit.ui.favorites;

import android.content.Context;

import com.aaamab.bonappetit.data.FavoritesData;
import com.aaamab.bonappetit.data.OffersData;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;

public class FavPresenter {
    FavInter fav;

    public FavPresenter(FavInter fav) {
        this.fav = fav;
    }

    public void getFavorites(String token, Context context) {
        boolean network = StaticMethods.isConnectingToInternet(context);
        if (network) {

            MainApi.getFavorites(token, LocaleManager.getLanguage(context), new ConnectionListener<FavoritesData>() {
                @Override
                public void onSuccess(ConnectionResponse<FavoritesData> connectionResponse) {
                    if(connectionResponse.data.getCode() == 200 ){
                        fav.onSuccess(connectionResponse.data);
                        StaticMethods.printJson("data",connectionResponse.data);
                    }else {
                        fav.onFail(connectionResponse.data.msg);
                    }

                }

                @Override
                public void onFail(Throwable throwable) {
                    fav.onFail("Server Error");
                }
            });
        } else {
            fav.onConnection(true);
        }
    }
}
