package com.aaamab.bonappetit.ui.search;

import android.content.Context;

import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.data.SeatingData;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

public class SearchPresenter {
    SearchInter search;

    public SearchPresenter(SearchInter search) {
        this.search = search;
    }

    public void onSearch(Context context, RequestBody body) {
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {
            MainApi.filterBook(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<RestArray>() {
                @Override
                public void onSuccess(ConnectionResponse<RestArray> connectionResponse) {
                    if (connectionResponse.data.code == 200) {
                        search.onSuccess(connectionResponse.data);
                    } else {
                        search.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    search.onFail(throwable.getMessage());
                }
            });
        } else {
            search.onConnection(true);
        }
    }

    public void getSeating(Context context, int restId) {
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {
            RequestBody body = null;
            MainApi.getSeatingNoId(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<SeatingData>() {
                @Override
                public void onSuccess(ConnectionResponse<SeatingData> connectionResponse) {
                    if (connectionResponse.data.code == 200) {
                        search.onSeatingType(connectionResponse.data);
                    } else {
                        search.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    search.onFail(throwable.getMessage());
                }
            });
        } else {
            search.onConnection(true);
        }
    }
}
