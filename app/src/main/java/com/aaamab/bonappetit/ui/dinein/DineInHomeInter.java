package com.aaamab.bonappetit.ui.dinein;

import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.Upcomming;

public interface DineInHomeInter {

    void onSuccess(RestArray array);
    void onFail(String error);
    void onConnection(boolean isConnected);
    void onUpComing(Upcomming array);
    void isRated(boolean isRated);


}
