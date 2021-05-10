package com.aaamab.bonappetit.ui.menus;

import com.aaamab.bonappetit.data.FoodData;
import com.aaamab.bonappetit.data.RemoveFood;
import com.aaamab.bonappetit.data.RemoveFoodOBJ;
import com.aaamab.bonappetit.data.RestMenu;
import com.aaamab.bonappetit.data.RestruantByID;

public interface MenuInter {
    void onSuccess(RestruantByID restruant);
    void onFail(String error);
    void onConnection(boolean isConnected);
    void onApply(boolean status , RemoveFood<RemoveFoodOBJ> data);
    void onFood(FoodData data);

    void onMenuFood(RestMenu data) ;
}
