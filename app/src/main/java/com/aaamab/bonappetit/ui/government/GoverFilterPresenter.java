package com.aaamab.bonappetit.ui.government;

import android.content.Context;

import com.aaamab.bonappetit.data.FilterData;
import com.aaamab.bonappetit.data.FilterObj;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;

public class GoverFilterPresenter {
    GovFilterInterface gover ;

    public GoverFilterPresenter(GovFilterInterface gover) {
        this.gover = gover;
    }

    public void onGovernment(Context context ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            MainApi.getFilterData(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), new ConnectionListener<FilterData<FilterObj>>() {
                @Override
                public void onSuccess(ConnectionResponse<FilterData<FilterObj>> connectionResponse) {
                    if(connectionResponse.data.code == 200){
                        gover.onSuccess(connectionResponse.data);
                    }else {
                        gover.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    gover.onFail(throwable.getMessage());
                }
            });
        }else {
            gover.onConnection(true);
        }
    }
}
