package com.aaamab.bonappetit.ui.government;

import com.aaamab.bonappetit.data.FilterData;
import com.aaamab.bonappetit.data.FilterObj;

public interface GovFilterInterface {
    void onSuccess(FilterData<FilterObj> data);
    void onFail(String error);
    void onConnection(boolean isConnected);
}
