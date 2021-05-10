package com.aaamab.bonappetit.ui.profile;

import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.LoginData;
import com.aaamab.bonappetit.data.ProfileData;
import com.aaamab.bonappetit.data.ProfileDataPra;

public interface ProfileInter {
    void onSuccess(DataObj<ProfileData<ProfileDataPra>> data );
    void onFail(String error);
    void onConnection(boolean isConnected);
    void onChangePass(boolean status);

    void onDataChanged(DataObj<LoginData> userData);
}
