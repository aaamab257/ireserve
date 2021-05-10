package com.aaamab.bonappetit.ui.register;

import com.aaamab.bonappetit.data.LoginData;
import com.aaamab.bonappetit.data.RegisterData;

public interface RegInter {
    void onSuccess(RegisterData data);
    void onFail(String error);
    void onConnection(boolean isConnected);
}
