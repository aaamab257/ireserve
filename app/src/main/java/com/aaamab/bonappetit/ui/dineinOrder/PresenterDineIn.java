package com.aaamab.bonappetit.ui.dineinOrder;

import android.content.Context;
import android.util.Log;

import com.aaamab.bonappetit.data.MakeBook;
import com.aaamab.bonappetit.data.MakeBookings;
import com.aaamab.bonappetit.data.MakePaymentOBJ;
import com.aaamab.bonappetit.data.NewMakeBook;
import com.aaamab.bonappetit.data.PaymentSummry;
import com.aaamab.bonappetit.data.SeatingData;
import com.aaamab.bonappetit.ui.paymentMethod.PaymentSummary;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;
import com.aaamab.bonappetit.utils.network.MainApiBody;

import okhttp3.RequestBody;

import static android.content.ContentValues.TAG;

public class PresenterDineIn {
    InterfaceDineIn dine;
    PaymentSummary paymentSummary ;



    public PresenterDineIn(InterfaceDineIn dine ,PaymentSummary paymentSummary) {
        this.dine = dine;
        this.paymentSummary = paymentSummary;
    }

    public void makeBookStatus(Context context, int id, String date, String time, String count , int seat) {
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {
            RequestBody body = null;
            try {
                body = MainApiBody.makePaymentNew(id, count, date, time , "" , seat);
            } catch (Exception e) {

            }

            MainApi.makePAyStatus(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<NewMakeBook>() {
                @Override
                public void onSuccess(ConnectionResponse<NewMakeBook> connectionResponse) {
                    StaticMethods.printJson("json" , connectionResponse.data);
                    if (connectionResponse.data.getCode() == 200) {
                        dine.onSuccessStatus(connectionResponse.data);
                    } else {
                        dine.onFail(connectionResponse.data.getMsg());
                        Log.e(TAG, "onSuccess: "+ connectionResponse.data.getMsg());
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    dine.onFail(throwable.getMessage());
                    Log.e(TAG, "onFail: "+ throwable.getMessage());
                }
            });
        } else {
            dine.onConnection(true);
        }
    }
    public void makeBook(Context context, int id, String date, String time, String count , String pay) {
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {
            RequestBody body = null;
            try {
                body = MainApiBody.makePayment(id, count, date, time ,pay);
            } catch (Exception e) {

            }
            MainApi.makePAy(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<MakeBook>() {
                @Override
                public void onSuccess(ConnectionResponse<MakeBook> connectionResponse) {
                    if (connectionResponse.data.stu) {
                        dine.onSuccess(connectionResponse.data);
                    } else {
                        dine.onFail("Error");
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    dine.onFail(throwable.getMessage());
                }
            });
        } else {
            dine.onConnection(true);
        }
    }
    public void getSeating(Context context, int restId) {
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {
            RequestBody body = null;
            try {
                body = MainApiBody.seatingBody(restId);
            } catch (Exception e) {

            }
            MainApi.getSeatingTypes(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<SeatingData>() {
                @Override
                public void onSuccess(ConnectionResponse<SeatingData> connectionResponse) {
                    if (connectionResponse.data.code == 200) {
                        dine.onSeatingType(connectionResponse.data);
                    } else {
                        dine.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    dine.onFail(throwable.getMessage());
                }
            });
        } else {
            dine.onConnection(true);
        }
    }
    public void getSummary(Context context , RequestBody body){
        boolean net = StaticMethods.isConnectingToInternet(context);
        if (net) {

            MainApi.getPaymentSu(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), body, new ConnectionListener<PaymentSummry>() {
                @Override
                public void onSuccess(ConnectionResponse<PaymentSummry> connectionResponse) {
                    if (connectionResponse.data.code == 200) {
                        paymentSummary.onPayment(connectionResponse.data);
                    } else {
                        dine.onFail(connectionResponse.data.msg);
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    dine.onFail(throwable.getMessage());
                }
            });
        } else {
            dine.onConnection(true);
        }
    }

}
