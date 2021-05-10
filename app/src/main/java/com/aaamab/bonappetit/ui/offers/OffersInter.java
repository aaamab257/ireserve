package com.aaamab.bonappetit.ui.offers;

import com.aaamab.bonappetit.data.LoginData;
import com.aaamab.bonappetit.data.OffersData;

public interface OffersInter {
    void onSuccess(OffersData data);
    void onFail(String error);
    void onConnection(boolean isConnected);
}
