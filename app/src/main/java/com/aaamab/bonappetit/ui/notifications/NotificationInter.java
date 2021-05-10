package com.aaamab.bonappetit.ui.notifications;

import com.aaamab.bonappetit.data.NotificationArray;

public interface NotificationInter {
    void onNotification(NotificationArray array);
    void onFail(String error );
    void onConnection(boolean isConnected);

}
