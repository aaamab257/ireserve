package com.aaamab.bonappetit.ui.contactUs;

import android.content.Context;

import com.aaamab.bonappetit.data.ContactUsData;
import com.aaamab.bonappetit.data.ContactUsObj;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;

import okhttp3.RequestBody;

public class ContactUsPresenter {
    ContactUsIter contactUsIter ;

    public ContactUsPresenter(ContactUsIter contactUsIter) {
        this.contactUsIter = contactUsIter;
    }

    public void sendContact(Context context , RequestBody body ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if(net){
            MainApi.contactUs(StaticMethods.userData.getApi_token(), body, new ConnectionListener<ContactUsData<ContactUsObj>>() {
                @Override
                public void onSuccess(ConnectionResponse<ContactUsData<ContactUsObj>> connectionResponse) {
                    if (connectionResponse.data.code == 200){
                        contactUsIter.onSuccess(connectionResponse.data);
                    }else {
                        contactUsIter.onFail(connectionResponse.data.getMsg());
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    contactUsIter.onFail(throwable.getMessage());
                }
            });
        }else {
            contactUsIter.onConnection(true);
        }
    }
}
