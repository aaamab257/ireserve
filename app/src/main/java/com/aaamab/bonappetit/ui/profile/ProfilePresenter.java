package com.aaamab.bonappetit.ui.profile;

import android.content.Context;

import com.aaamab.bonappetit.data.ChangePass;
import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.LoginData;
import com.aaamab.bonappetit.data.ProfileData;
import com.aaamab.bonappetit.data.ProfileDataPra;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

public class ProfilePresenter {
    ProfileInter profile;

    public ProfilePresenter(ProfileInter profile) {
        this.profile = profile;
    }

    public void profileData(Context context) {
        boolean net = StaticMethods.isConnectingToInternet(context);

        if (net) {

            MainApi.getProfile(StaticMethods.userData.api_token, LocaleManager.getLanguage(context), new ConnectionListener<DataObj<ProfileData<ProfileDataPra>>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<ProfileData<ProfileDataPra>>> connectionResponse) {
                    if (connectionResponse.data.data != null) {
                        profile.onSuccess(connectionResponse.data);

                    } else {
                        profile.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    profile.onFail("Server Error");
                }
            });
        } else {
            profile.onConnection(true);
        }
    }
    public void changePass(Context context , String old , String newp , String confirm) {
        boolean net = StaticMethods.isConnectingToInternet(context);

        if (net) {

            RequestBody body = null ;
            try {
                body = MainApiBody.changePas(old , newp , confirm);
            }catch (Exception e){

            }


            MainApi.changePassword(StaticMethods.userData.api_token,body, new ConnectionListener<DataObj<ChangePass>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<ChangePass>> connectionResponse) {
                    if (connectionResponse.data.data != null) {
                        profile.onChangePass(connectionResponse.data.data.isStatus());

                    } else {
                        profile.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    profile.onFail("Server Error");
                }
            });
        } else {
            profile.onConnection(true);
        }
    }
    public void changeData(Context context ,String name , String email , String phone , String loca, String gender) {
        boolean net = StaticMethods.isConnectingToInternet(context);

        if (net) {

            RequestBody body = null ;
            try {
                body = MainApiBody.updateUserData(name , email , phone , loca ,gender);
            }catch (Exception e){

            }


            MainApi.updateDate(StaticMethods.userData.api_token,body, LocaleManager.getLanguage(context), new ConnectionListener<DataObj<LoginData>>() {
                @Override
                public void onSuccess(ConnectionResponse<DataObj<LoginData>> connectionResponse) {
                    if (connectionResponse.data.data != null) {
                        profile.onDataChanged(connectionResponse.data);
                    } else {
                        profile.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    profile.onFail("Server Error");
                }
            });
        } else {
            profile.onConnection(true);
        }

    }
}
