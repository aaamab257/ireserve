package com.aaamab.bonappetit.ui.boooking;

import android.content.Context;

import com.aaamab.bonappetit.data.BookingArray;
import com.aaamab.bonappetit.utils.LocaleManager;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.aaamab.bonappetit.utils.network.ConnectionListener;
import com.aaamab.bonappetit.utils.network.ConnectionResponse;
import com.aaamab.bonappetit.utils.network.MainApi;

public class BookingsPresenter {
    BookingsInter bookingsInter ;

    public BookingsPresenter(BookingsInter bookingsInter) {
        this.bookingsInter = bookingsInter;
    }

    public void getBooking(Context context ){
        boolean net = StaticMethods.isConnectingToInternet(context);

        if(net){
            MainApi.bookings(StaticMethods.userData.getApi_token(), LocaleManager.getLanguage(context), new ConnectionListener<BookingArray>() {
                @Override
                public void onSuccess(ConnectionResponse<BookingArray> connectionResponse) {
                    if(connectionResponse.data.getCode() == 200){
                        bookingsInter.onSuccess(connectionResponse.data);
                    }else {
                        bookingsInter.onFail(connectionResponse.data.getMsg());
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    bookingsInter.onFail(throwable.getMessage());
                }
            });
        }else {
            bookingsInter.onConnection(true);
        }
    }
}
