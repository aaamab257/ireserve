package com.aaamab.bonappetit.ui.myOrders;

import com.aaamab.bonappetit.data.AllMyOrders;

public interface MyOrdersInter {
    void onSuccess(AllMyOrders allMyOrders );
    void onFail(String error);
    void onConnection(boolean isConnected);

}
