package com.aaamab.bonappetit.ui.menus;

import android.content.Context;

import com.aaamab.bonappetit.R;
import com.aaamab.bonappetit.data.FoodData;
import com.aaamab.bonappetit.data.RemoveFood;
import com.aaamab.bonappetit.data.RemoveFoodOBJ;
import com.aaamab.bonappetit.data.RestMenu;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.ToastUtil;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

public class MenuPresenter {
    MenuInter menuInter ;

    public MenuPresenter(MenuInter menuInter) {
        this.menuInter = menuInter;
    }
    public void getDetails(Context context , int resID ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            RequestBody body = null ;
            try {
                body = MainApiBody.res(resID);
            }catch (Exception e){

            }
            MainApi.getResById(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<RestruantByID>() {
                @Override
                public void onSuccess(ConnectionResponse<RestruantByID> connectionResponse) {
                    if (connectionResponse.data.getRes().size() > 0 ){
                        menuInter.onSuccess(connectionResponse.data);
                    }else {
                        menuInter.onFail("No Data To Show");
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    menuInter.onFail("Server error , try again");
                }
            });
        }else {
            menuInter.onConnection(true);
        }
    }
    public void getMenu(Context context , int resID ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            RequestBody body = null ;
            try {
                body = MainApiBody.res(resID);
            }catch (Exception e){

            }
            MainApi.getFoods(StaticMethods.userData.getApi_token(),LocaleManager.getLanguage(context), body, new ConnectionListener<FoodData>() {
                @Override
                public void onSuccess(ConnectionResponse<FoodData> connectionResponse) {
                    if (connectionResponse.data.getFa().size() > 0 ){
                        menuInter.onFood(connectionResponse.data);
                    }else {
                        menuInter.onFail("No Data To Show");
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    menuInter.onFail("Server error , try again");
                }
            });
        }else {
            menuInter.onConnection(true);
        }
    }

    public void applyOrder(final Context context , int resID , String type , final String pay ){
        RequestBody body  = null ;
        try {
            body = MainApiBody.applyOrder(resID ,type , pay);
        }catch (Exception e){

        }

        MainApi.applyOrder(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<RemoveFood<RemoveFoodOBJ>>() {
            @Override
            public void onSuccess(ConnectionResponse<RemoveFood<RemoveFoodOBJ>> connectionResponse) {
                if (pay.equals("cash")){
                    if(connectionResponse.data.getCode() == 200){
                        menuInter.onApply(true ,connectionResponse.data);
                    }else {
                        ToastUtil.showErrorToast(context,connectionResponse.data.getMsg());
                    }
                }else {
                    if(connectionResponse.data.isStatus()){
                        menuInter.onApply(true ,connectionResponse.data);
                    }else {
                        ToastUtil.showErrorToast(context,connectionResponse.data.getMsg());
                    }
                }

            }

            @Override
            public void onFail(Throwable throwable) {
                ToastUtil.showErrorToast(context,throwable.getMessage());
            }
        });
    }

    public void getMenuData(final Context context , int resID ){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net){
            RequestBody body = null ;
            try {
                body = MainApiBody.res(resID);
            }catch (Exception e){

            }
            MainApi.getMenu(StaticMethods.userData.getApi_token() , LocaleManager.getLanguage(context), body, new ConnectionListener<RestMenu>() {
                @Override
                public void onSuccess(ConnectionResponse<RestMenu> connectionResponse) {
                    if (connectionResponse.data.getData().size() > 0 ){
                        menuInter.onMenuFood(connectionResponse.data);
                    }else {
                        menuInter.onFail(context.getString(R.string.no_data));
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    menuInter.onFail("Server error , try again");
                }
            });
        }else {
            menuInter.onConnection(true);
        }
    }
}
