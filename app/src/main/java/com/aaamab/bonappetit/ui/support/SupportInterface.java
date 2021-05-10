package com.aaamab.bonappetit.ui.support;

import com.aaamab.bonappetit.data.SupportArray;

public interface SupportInterface {
    void onSuccess(SupportArray array );
    void onFail(String error );
    void onConnection(boolean isConnected);

}
