package com.aaamab.bonappetit.utils.network;


import com.aaamab.bonappetit.data.AboutArray;
import com.aaamab.bonappetit.data.AddFoodArray;
import com.aaamab.bonappetit.data.AddFoodCheck;
import com.aaamab.bonappetit.data.AddFoodItem;
import com.aaamab.bonappetit.data.AllMyOrders;
import com.aaamab.bonappetit.data.AreaFilters;
import com.aaamab.bonappetit.data.BookingArray;
import com.aaamab.bonappetit.data.ChangePass;
import com.aaamab.bonappetit.data.ContactUsData;
import com.aaamab.bonappetit.data.ContactUsObj;
import com.aaamab.bonappetit.data.DataObj;
import com.aaamab.bonappetit.data.FavoritesData;
import com.aaamab.bonappetit.data.FilterData;
import com.aaamab.bonappetit.data.FilterObj;
import com.aaamab.bonappetit.data.FoodData;
import com.aaamab.bonappetit.data.LoginData;
import com.aaamab.bonappetit.data.MakeBook;
import com.aaamab.bonappetit.data.MakeBookings;
import com.aaamab.bonappetit.data.MakeFavorites;
import com.aaamab.bonappetit.data.MakePaymentOBJ;
import com.aaamab.bonappetit.data.MakeReview;
import com.aaamab.bonappetit.data.MyOrdersArray;
import com.aaamab.bonappetit.data.NewMakeBook;
import com.aaamab.bonappetit.data.NotificationArray;
import com.aaamab.bonappetit.data.OffersData;
import com.aaamab.bonappetit.data.PaymentMoreData;
import com.aaamab.bonappetit.data.PaymentSummry;
import com.aaamab.bonappetit.data.ProfileData;
import com.aaamab.bonappetit.data.ProfileDataPra;
import com.aaamab.bonappetit.data.Rating;
import com.aaamab.bonappetit.data.ReOrder;
import com.aaamab.bonappetit.data.RegisterData;
import com.aaamab.bonappetit.data.RemoveFood;
import com.aaamab.bonappetit.data.RemoveFoodOBJ;
import com.aaamab.bonappetit.data.RestArray;
import com.aaamab.bonappetit.data.RestMenu;
import com.aaamab.bonappetit.data.RestruantByID;
import com.aaamab.bonappetit.data.ResturantsData;
import com.aaamab.bonappetit.data.SeatingData;
import com.aaamab.bonappetit.data.SocialMediaData;
import com.aaamab.bonappetit.data.SupportArray;
import com.aaamab.bonappetit.data.TermsArray;
import com.aaamab.bonappetit.data.Upcomming;
import com.aaamab.bonappetit.utils.StaticMethods;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;

public class MainApi {
    private static MainApiInterface getApi() {
        return getApi(MainApiInterface.class);
    }

    public static <T> T getApi(Class<T> aClass) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StaticMethods.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        return retrofit.create(aClass);
    }

    public static void LoginApi(RequestBody body, final ConnectionListener<DataObj<LoginData>> connectionListener) {
        getApi().login(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataObj<LoginData>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DataObj<LoginData> userResponse) {
                        ConnectionResponse<DataObj<LoginData>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void updateDate(String token, RequestBody body , String lang, final ConnectionListener<DataObj<LoginData>> connectionListener) {
        getApi().updateProfile(token, body , lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataObj<LoginData>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DataObj<LoginData> userResponse) {
                        ConnectionResponse<DataObj<LoginData>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void RegisterApi(RequestBody body, final ConnectionListener<DataObj<RegisterData>> connectionListener) {
        getApi().register(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataObj<RegisterData>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DataObj<RegisterData> userResponse) {
                        ConnectionResponse<DataObj<RegisterData>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getOffers(String token , String lang, final ConnectionListener<OffersData> connectionListener) {
        getApi().offers(token , lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OffersData>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(OffersData userResponse) {
                        ConnectionResponse<OffersData> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getSupport(String token, String lang, String htoken, final ConnectionListener<SupportArray> connectionListener) {
        getApi().support(token, lang, htoken).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupportArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SupportArray userResponse) {
                        ConnectionResponse<SupportArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getNotifications(String token , String lang, final ConnectionListener<NotificationArray> connectionListener) {
        getApi().getNotifications(token, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NotificationArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(NotificationArray userResponse) {
                        ConnectionResponse<NotificationArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getAllMyOrders(String token,String lang, final ConnectionListener<AllMyOrders> connectionListener) {
        getApi().getAllMyOrders(token, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllMyOrders>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AllMyOrders userResponse) {
                        ConnectionResponse<AllMyOrders> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }
    public static void PayData(String token,String lang, final ConnectionListener<PaymentMoreData> connectionListener) {
        getApi().getPayData(token, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PaymentMoreData>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PaymentMoreData userResponse) {
                        ConnectionResponse<PaymentMoreData> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getSocial(String token , String lang, final ConnectionListener<SocialMediaData> connectionListener) {
        getApi().getSocial(token, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SocialMediaData>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SocialMediaData userResponse) {
                        ConnectionResponse<SocialMediaData> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }
    //getAllMyOrders
    public static void getFoods(String token , String lang, RequestBody body, final ConnectionListener<FoodData> connectionListener) {
        getApi().getFoods(token , lang, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodData>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(FoodData userResponse) {
                        StaticMethods.printJson("foodData", userResponse);
                        ConnectionResponse<FoodData> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getMenu(String token , String lang, RequestBody body, final ConnectionListener<RestMenu> connectionListener) {
        getApi().getMenuRest(token , lang, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RestMenu>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RestMenu userResponse) {
                        StaticMethods.printJson("foodData", userResponse);
                        ConnectionResponse<RestMenu> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void changePassword(String token, RequestBody body, final ConnectionListener<DataObj<ChangePass>> connectionListener) {
        getApi().changePass(token, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataObj<ChangePass>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DataObj<ChangePass> userResponse) {
                        StaticMethods.printJson("foodData", userResponse);
                        ConnectionResponse<DataObj<ChangePass>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getFavorites(String token , String lang, final ConnectionListener<FavoritesData> connectionListener) {
        getApi().favorites(token, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FavoritesData>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(FavoritesData userResponse) {
                        ConnectionResponse<FavoritesData> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getHome(String token, String lang, RequestBody body, final ConnectionListener<RestArray> connectionListener) {
        getApi().getRes(token,lang , body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RestArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RestArray userResponse) {
                        ConnectionResponse<RestArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getRes(String token,String lang , RequestBody body, final ConnectionListener<RestArray> connectionListener) {
        getApi().getResVIP(token,lang , body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RestArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RestArray userResponse) {
                        ConnectionResponse<RestArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void bookings(String token, String lang, final ConnectionListener<BookingArray> connectionListener) {
        getApi().getBookings(token , lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookingArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BookingArray userResponse) {
                        ConnectionResponse<BookingArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getProfile(String token , String lang, final ConnectionListener<DataObj<ProfileData<ProfileDataPra>>> connectionListener) {
        getApi().profile(token , lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataObj<ProfileData<ProfileDataPra>>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DataObj<ProfileData<ProfileDataPra>> userResponse) {
                        ConnectionResponse<DataObj<ProfileData<ProfileDataPra>>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getResById(String token, String lang, RequestBody body, final ConnectionListener<RestruantByID> connectionListener) {
        getApi().resByID(token, body, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RestruantByID>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RestruantByID userResponse) {
                        ConnectionResponse<RestruantByID> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void deleteOrder(String token , RequestBody body, final ConnectionListener<DataObj<ChangePass>> connectionListener) {
        getApi().deleteOrder(token ,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataObj<ChangePass>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DataObj<ChangePass> userResponse) {
                        ConnectionResponse<DataObj<ChangePass>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void postReview(String token, RequestBody body, final ConnectionListener<DataObj<MakeReview>> connectionListener) {
        getApi().makeReview(token, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataObj<MakeReview>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DataObj<MakeReview> userResponse) {
                        ConnectionResponse<DataObj<MakeReview>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }


    public static void postFav(String token, RequestBody body, final ConnectionListener<DataObj<MakeFavorites>> connectionListener) {
        getApi().makeFav(token, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataObj<MakeFavorites>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DataObj<MakeFavorites> userResponse) {
                        ConnectionResponse<DataObj<MakeFavorites>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void removeFav(String token, RequestBody body, final ConnectionListener<DataObj<MakeFavorites>> connectionListener) {
        getApi().removeFav(token, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataObj<MakeFavorites>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DataObj<MakeFavorites> userResponse) {
                        ConnectionResponse<DataObj<MakeFavorites>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void removeFood(String token , String lang, RequestBody body, final ConnectionListener<RemoveFood<RemoveFoodOBJ>> connectionListener) {
        getApi().removeFood(token,lang, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RemoveFood<RemoveFoodOBJ>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RemoveFood<RemoveFoodOBJ> userResponse) {
                        ConnectionResponse<RemoveFood<RemoveFoodOBJ>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void applyOrder(String token, String lang, RequestBody body, final ConnectionListener<RemoveFood<RemoveFoodOBJ>> connectionListener) {
        getApi().applyOrder(token, body, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RemoveFood<RemoveFoodOBJ>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RemoveFood<RemoveFoodOBJ> userResponse) {
                        ConnectionResponse<RemoveFood<RemoveFoodOBJ>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void addFood(String token , String lang, RequestBody body, final ConnectionListener<AddFoodArray> connectionListener) {
        getApi().addFood(token , lang, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddFoodArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AddFoodArray userResponse) {
                        ConnectionResponse<AddFoodArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }
    public static void addFoodCheck(String token , String lang, RequestBody body, final ConnectionListener<AddFoodCheck> connectionListener) {
        getApi().addFoodCheck(token , lang, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddFoodCheck>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AddFoodCheck userResponse) {
                        ConnectionResponse<AddFoodCheck> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void myOrder(String token, String lang , RequestBody body, final ConnectionListener<MyOrdersArray> connectionListener) {
        getApi().myOrder(token, lang , body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyOrdersArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(MyOrdersArray userResponse) {
                        ConnectionResponse<MyOrdersArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void terms(String lang ,final ConnectionListener<TermsArray> connectionListener) {
        getApi().terms(lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TermsArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(TermsArray userResponse) {
                        ConnectionResponse<TermsArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void about(String token, String lang, final ConnectionListener<AboutArray> connectionListener) {
        getApi().aboutUs(token , lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AboutArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AboutArray userResponse) {
                        ConnectionResponse<AboutArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void contactUs(String token, RequestBody body, final ConnectionListener<ContactUsData<ContactUsObj>> connectionListener) {
        getApi().contactUs(token, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContactUsData<ContactUsObj>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ContactUsData<ContactUsObj> userResponse) {
                        ConnectionResponse<ContactUsData<ContactUsObj>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void makePAy(String token, String lang, RequestBody body, final ConnectionListener<MakeBook> connectionListener) {
        getApi().make_booking(token, body , lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MakeBook>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(MakeBook userResponse) {
                        ConnectionResponse<MakeBook> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }
    public static void makePAyStatus(String token, String lang, RequestBody body, final ConnectionListener<NewMakeBook> connectionListener) {
        getApi().make_bookingStatus(token, body , lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewMakeBook>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(NewMakeBook userResponse) {
                        ConnectionResponse<NewMakeBook> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void reOrder(String token, String lang, RequestBody body, final ConnectionListener<ReOrder> connectionListener) {
        getApi().reOrder(token, lang, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReOrder>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ReOrder userResponse) {
                        ConnectionResponse<ReOrder> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void postFilter(String token, String lang, Map<String,String> body, final ConnectionListener<RestArray> connectionListener) {
        getApi().postFilter(token, lang, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RestArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RestArray userResponse) {
                        ConnectionResponse<RestArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getFilterData(String token, String lang, final ConnectionListener<FilterData<FilterObj>> connectionListener) {
        getApi().getFilter(token, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FilterData<FilterObj>>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(FilterData<FilterObj> userResponse) {
                        ConnectionResponse<FilterData<FilterObj>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getMall(String token, String lang, Map<String,String> body, final ConnectionListener<AreaFilters> connectionListener) {
        getApi().getMall(token, lang,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaFilters>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AreaFilters userResponse) {
                        ConnectionResponse<AreaFilters> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getAreaMallS(String token, String lang, Map<String,String> body, final ConnectionListener<AreaFilters> connectionListener) {
        getApi().getAreaMall(token, lang,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaFilters>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AreaFilters userResponse) {
                        ConnectionResponse<AreaFilters> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void filterBook(String token, String lang, RequestBody body, final ConnectionListener<RestArray> connectionListener) {
        getApi().filterBooking(token, lang, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RestArray>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RestArray userResponse) {
                        ConnectionResponse<RestArray> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void upcoming(String token, String lang, final ConnectionListener<Upcomming> connectionListener) {
        getApi().upComing(token, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Upcomming>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Upcomming userResponse) {
                        ConnectionResponse<Upcomming> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }
    public static void isRated(String token, String lang, final ConnectionListener<Rating> connectionListener) {
        getApi().ratedOrNot(token, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Rating>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Rating userResponse) {
                        ConnectionResponse<Rating> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }
    public static void cancelRate(String token, String lang, final ConnectionListener<Rating> connectionListener) {
        getApi().cancelRating(token, lang).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Rating>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Rating userResponse) {
                        ConnectionResponse<Rating> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }
    public static void rate(String token , String lang, RequestBody body , final ConnectionListener<Rating> connectionListener) {
        getApi().rateOnApply(token, lang ,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Rating>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Rating userResponse) {
                        ConnectionResponse<Rating> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }

    public static void getSeatingTypes(String token, String lang,RequestBody body , final ConnectionListener<SeatingData> connectionListener) {
        getApi().getSeating(token, lang , body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SeatingData>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SeatingData userResponse) {
                        ConnectionResponse<SeatingData> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }
    public static void getSeatingNoId(String token, String lang,RequestBody body , final ConnectionListener<SeatingData> connectionListener) {
        getApi().getSeatingWithoutId(token, lang ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SeatingData>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SeatingData userResponse) {
                        ConnectionResponse<SeatingData> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }
    public static void getPaymentSu(String token, String lang,RequestBody body , final ConnectionListener<PaymentSummry> connectionListener) {
        getApi().getPaySummary(token, lang,body ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PaymentSummry>() {
                    @Override
                    public void onError(Throwable e) {
                        connectionListener.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PaymentSummry userResponse) {
                        ConnectionResponse<PaymentSummry> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                });
    }
}
