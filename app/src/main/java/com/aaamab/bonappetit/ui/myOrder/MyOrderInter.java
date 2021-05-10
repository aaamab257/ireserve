package com.aaamab.bonappetit.ui.myOrder;

import com.aaamab.bonappetit.data.ChangePass;
import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.MyOrdersArray;

public interface MyOrderInter {
    void onMyOrder(MyOrdersArray array );
    void onFail(String error );
    void onConnection(boolean isConnected);
    void onDeleteOrder(DataObj<ChangePass> dataObj );
}
