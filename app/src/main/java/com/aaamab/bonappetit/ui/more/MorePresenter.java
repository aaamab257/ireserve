package com.aaamab.bonappetit.ui.more;

import android.content.Context;

import com.aaamab.bonappetit.data.SocialMediaData;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;

public class MorePresenter {
    MoreInterface more ;

    public MorePresenter(MoreInterface more) {
        this.more = more;
    }

    public void getSocial(Context context){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            MainApi.getSocial(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), new ConnectionListener<SocialMediaData>() {
                @Override
                public void onSuccess(ConnectionResponse<SocialMediaData> connectionResponse) {
                    if (connectionResponse.data.code == 200){
                        more.onSocial(connectionResponse.data);
                    }else {
                        more.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    more.onFail(throwable.getMessage());
                }
            });
        }else {
            more.onConnection(true);
        }
    }
}
