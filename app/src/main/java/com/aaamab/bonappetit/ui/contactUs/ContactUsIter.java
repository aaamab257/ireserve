package com.aaamab.bonappetit.ui.contactUs;

import com.aaamab.bonappetit.data.ContactUsData;
import com.aaamab.bonappetit.data.ContactUsObj;

public interface ContactUsIter {
    void onSuccess(ContactUsData<ContactUsObj> data );
    void onFail(String error);
    void onConnection(boolean isConnected);

}
