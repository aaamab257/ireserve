package com.aaamab.bonappetit.ui.notifications;

import android.content.Context;

import com.aaamab.bonappetit.data.NotificationArray;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;

public class NotificationPresenter {
    NotificationInter notificationInter ;

    public NotificationPresenter(NotificationInter notificationInter) {
        this.notificationInter = notificationInter;
    }

    public void getNotifi(Context context){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if(net){
            MainApi.getNotifications(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), new ConnectionListener<NotificationArray>() {
                @Override
                public void onSuccess(ConnectionResponse<NotificationArray> connectionResponse) {
                    if(connectionResponse.data.getNotificationItems().size() > 0){
                        notificationInter.onNotification(connectionResponse.data);
                    }else {
                        notificationInter.onFail(connectionResponse.data.getMsg());
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    notificationInter.onFail(throwable.getMessage());
                }
            });
        }else {
            notificationInter.onConnection(true);
        }
    }
}
