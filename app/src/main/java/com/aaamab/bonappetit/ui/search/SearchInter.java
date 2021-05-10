package com.aaamab.bonappetit.ui.search;

import com.aaamab.bonappetit.data.RegisterData;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.data.SeatingData;

public interface SearchInter {
    void onSuccess(RestArray data);
    void onFail(String error);
    void onConnection(boolean isConnected);
    void onSeatingType(SeatingData data);
}
