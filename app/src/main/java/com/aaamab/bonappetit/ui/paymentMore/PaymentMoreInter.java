package com.aaamab.bonappetit.ui.paymentMore;

import com.aaamab.bonappetit.data.PaymentMoreData;

public interface PaymentMoreInter {
    void onSuccess(PaymentMoreData data );
    void onFail(String error);
    void onConnection(boolean isConnected);

}
