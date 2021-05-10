package com.aaamab.bonappetit.ui.about;

import android.content.Context;

import com.aaamab.bonappetit.data.AboutArray;
import com.aaamab.bonappetit.data.TermsArray;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;

public class AboutPresenter {
    AboutInter aboutInter ;

    public AboutPresenter(AboutInter aboutInter) {
        this.aboutInter = aboutInter;
    }

    public void getAbout(Context context ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if(net){
            MainApi.about(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), new ConnectionListener<AboutArray>() {
                @Override
                public void onSuccess(ConnectionResponse<AboutArray> connectionResponse) {
                    if(connectionResponse.data.code == 200){
                        aboutInter.onSuccess(connectionResponse.data);
                    }else {
                        aboutInter.onFail(connectionResponse.data.getMsg());
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    aboutInter.onFail(throwable.getMessage());
                }
            });
        }else {
            aboutInter.onConnection(true);
        }
    }
    public void getTerms(Context context){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if(net){
            MainApi.terms(LocaleManager.getLanguage(context), new ConnectionListener<TermsArray>() {
                @Override
                public void onSuccess(ConnectionResponse<TermsArray> connectionResponse) {
                    if(connectionResponse.data.code == 200){
                        aboutInter.onTerms(connectionResponse.data);
                    }else {
                        aboutInter.onFail(connectionResponse.data.getMsg());
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    aboutInter.onFail(throwable.getMessage());
                }
            });
        }else {
            aboutInter.onConnection(true);
        }
    }
}
