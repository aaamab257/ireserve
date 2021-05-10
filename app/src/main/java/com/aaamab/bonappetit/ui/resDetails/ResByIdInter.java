package com.aaamab.bonappetit.ui.resDetails;

import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.MakeFavorites;
import com.aaamab.bonappetit.data.RestruantByID;

public interface ResByIdInter {
    void onSuccess(RestruantByID restruant);
    void onFail(String error);
    void onConnection(boolean isConnected);
    void onMakeFav(DataObj<MakeFavorites> dataObj);
    void onRemoveFav(DataObj<MakeFavorites> dataObj);
}
