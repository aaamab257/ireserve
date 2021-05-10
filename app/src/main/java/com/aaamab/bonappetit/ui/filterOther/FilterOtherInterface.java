package com.aaamab.bonappetit.ui.filterOther;

import com.aaamab.bonappetit.data.AreaFilters;
import com.aaamab.bonappetit.data.FilterData;
import com.aaamab.bonappetit.data.FilterObj;

public interface FilterOtherInterface {
    void onArea(AreaFilters data );
    void onMall(AreaFilters data );
    void onCuis(FilterData<FilterObj> data);

    void onFail(String error);
    void onConnection(boolean isConnected);

}
