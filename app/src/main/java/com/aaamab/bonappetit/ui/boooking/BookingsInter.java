package com.aaamab.bonappetit.ui.boooking;

import com.aaamab.bonappetit.data.BookingArray;

public interface BookingsInter {
    void onSuccess(BookingArray array);
    void onFail(String error);
    void onConnection(boolean isConnected);

}
