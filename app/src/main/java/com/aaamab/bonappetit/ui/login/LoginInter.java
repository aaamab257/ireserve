package com.aaamab.bonappetit.ui.login;

import com.aaamab.bonappetit.data.LoginData;

public interface LoginInter {

    void onSuccess(LoginData data);
    void onFail(String error);
    void onConnection(boolean isConnected);

    void asGuest(LoginData guestData) ;
}
