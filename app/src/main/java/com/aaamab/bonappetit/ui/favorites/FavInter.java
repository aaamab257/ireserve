package com.aaamab.bonappetit.ui.favorites;

import com.aaamab.bonappetit.data.FavoritesData;
import com.aaamab.bonappetit.data.OffersData;

public interface FavInter {
    void onSuccess(FavoritesData data);
    void onFail(String error);
    void onConnection(boolean isConnected);
}
