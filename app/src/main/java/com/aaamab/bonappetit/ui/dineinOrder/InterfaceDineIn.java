package com.aaamab.bonappetit.ui.dineinOrder;

import com.aaamab.bonappetit.data.MakeBook;
import com.aaamab.bonappetit.data.MakeBookings;
import com.aaamab.bonappetit.data.MakePaymentOBJ;
import com.aaamab.bonappetit.data.NewMakeBook;
import com.aaamab.bonappetit.data.SeatingData;

public interface InterfaceDineIn {
    void onSuccess(MakeBook data);
    void onFail(String error);
    void onConnection(boolean isConnected);
    void onSeatingType(SeatingData data);
    void onSuccessStatus(NewMakeBook data);
}
