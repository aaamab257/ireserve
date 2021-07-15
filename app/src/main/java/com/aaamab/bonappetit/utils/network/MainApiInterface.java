package com.aaamab.bonappetit.utils.network;


import com.aaamab.bonappetit.data.AboutArray;
import com.aaamab.bonappetit.data.AddFoodArray;
import com.aaamab.bonappetit.data.AddFoodCheck;
import com.aaamab.bonappetit.data.AllMyOrders;
import com.aaamab.bonappetit.data.AreaFilters;
import com.aaamab.bonappetit.data.BookingArray;
import com.aaamab.bonappetit.data.ChangePass;
import com.aaamab.bonappetit.data.CityItem;
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
import com.aaamab.bonappetit.data.SeatingData;
import com.aaamab.bonappetit.data.SocialMediaData;
import com.aaamab.bonappetit.data.SupportArray;
import com.aaamab.bonappetit.data.TermsArray;
import com.aaamab.bonappetit.data.Upcomming;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MainApiInterface {

    //Auth
    @POST("login")
    Observable<DataObj<LoginData>> login(@Body RequestBody requestBody);

    @POST("continue_as_guest")
    Observable<DataObj<LoginData>> continueAsGuest(@Body RequestBody requestBody, @Header("lang") String lang);

    @POST("sign_up")
    Observable<DataObj<RegisterData>> register(@Body RequestBody requestBody);


    @GET("offers")
    Observable<OffersData> offers(@Query("api_token") String api_token, @Header("lang") String lang);

    @GET("favorits")
    Observable<FavoritesData> favorites(@Query("api_token") String api_token, @Header("lang") String lang);


    @POST("rest_by_cat")
    Observable<RestArray> getRes(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);

    @POST("get_food")
    Observable<FoodData> getFoods(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);


    @POST("add_food")
    Observable<AddFoodArray> addFood(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);

    @POST("add_food_check")
    Observable<AddFoodCheck> addFoodCheck(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);

    @POST("remove_food")
    Observable<RemoveFood<RemoveFoodOBJ>> removeFood(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);

    @POST("apply_order")
    Observable<RemoveFood<RemoveFoodOBJ>> applyOrder(@Query("api_token") String token, @Body RequestBody body, @Header("lang") String en);


    @POST("my_order")
    Observable<MyOrdersArray> myOrder(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);

    @GET("my_profile")
    Observable<DataObj<ProfileData<ProfileDataPra>>> profile(@Query("api_token") String api_token, @Header("lang") String lang);

    @POST("order_delete_all")
    Observable<DataObj<ChangePass>> deleteOrder(@Query("api_token") String api_token, @Body RequestBody body);

    //
    @POST("password/forgot")
    Observable<RestArray> forgetPass(@Body RequestBody body);

    //
    @GET("support")
    Observable<SupportArray> support(@Query("api_token") String api_token, @Header("lang") String lang, @Header("api_token") String token);

    //changePass
    @POST("changePass")
    Observable<DataObj<ChangePass>> changePass(@Query("api_token") String api_token, @Body RequestBody body);

    //update_user_data
    @POST("update_user_data")
    Observable<DataObj<LoginData>> updateProfile(@Query("api_token") String token, @Body RequestBody body, @Header("lang") String lang);

    //MAIN
//rest_vip_by_cat
    @POST("rest_vip_by_cat")
    Observable<RestArray> getResVIP(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);

    //
    @GET("about_us")
    Observable<AboutArray> aboutUs(@Query("api_token") String token, @Header("lang") String lang);

    //
    @GET("all_booking")
    Observable<BookingArray> getBookings(@Query("api_token") String token, @Header("lang") String lang);

    @GET("terms")
    Observable<TermsArray> terms(@Header("lang") String lang);

    //
    @POST("contact_us")
    Observable<ContactUsData<ContactUsObj>> contactUs(@Query("api_token") String token, @Body RequestBody body);

    //all_orders
    @GET("all_orders")
    Observable<AllMyOrders> getAllMyOrders(@Query("api_token") String token, @Header("lang") String lang);

    //Notifications
    @GET("notifications")
    Observable<NotificationArray> getNotifications(@Query("api_token") String token, @Header("lang") String lang);

    @POST("view_notify")
    Observable<RestArray> viewNotifications(@Query("api_token") String token, @Body RequestBody body);


    //rest_by_id
    @POST("rest_by_id")
    Observable<RestruantByID> resByID(@Query("api_token") String token, @Body RequestBody body, @Header("lang") String lang);
    //make_review

    @POST("make_review")
    Observable<DataObj<MakeReview>> makeReview(@Query("api_token") String token, @Body RequestBody body);

    @POST("make_favorit")
    Observable<DataObj<MakeFavorites>> makeFav(@Query("api_token") String token, @Body RequestBody body);

    @POST("remove_favorit")
    Observable<DataObj<MakeFavorites>> removeFav(@Query("api_token") String token, @Body RequestBody body);

    //make_booking
    @POST("Dian_in")
    Observable<NewMakeBook> make_bookingStatus(@Query("api_token") String token, @Body RequestBody body, @Header("lang") String en);

    @POST("make_booking")
    Observable<MakeBook> make_booking(@Query("api_token") String token, @Body RequestBody body, @Header("lang") String en);

    //Re_order
    @POST("Re_order")
    Observable<ReOrder> reOrder(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);


    //
    @GET("get_filter_data")
    Observable<FilterData<FilterObj>> getFilter(@Query("api_token") String token, @Header("lang") String lang);

    //get_zone_data
    @FormUrlEncoded
    @POST("get_government_data")
    Observable<AreaFilters> getAreaMall(@Query("api_token") String token, @Header("lang") String lang, @FieldMap Map<String, String> body);

    //get_area_data
    @FormUrlEncoded
    @POST("get_area_data")
    Observable<AreaFilters> getMall(@Query("api_token") String token, @Header("lang") String lang, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("filter")
    Observable<RestArray> postFilter(@Query("api_token") String token, @Header("lang") String lang, @FieldMap Map<String, String> body);

    //filter_booking

    @POST("filter_booking")
    Observable<RestArray> filterBooking(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);

    //upcoming
    @POST("upcoming")
    Observable<Upcomming> upComing(@Query("api_token") String token, @Header("lang") String lang);


    //
    //Rating
    @POST("last_order_evalute_or_not")
    Observable<Rating> ratedOrNot(@Query("api_token") String token, @Header("lang") String lang);

    @POST("last_order_evalute_cancle")
    Observable<Rating> cancelRating(@Query("api_token") String token, @Header("lang") String lang);


    @POST("Seat_Type")
    Observable<SeatingData> getSeating(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody bod);


    @POST("review")
    Observable<Rating> rateOnApply(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody bod);

    //Seat_Type_without_rest_id
    @POST("Seat_Type_without_rest_id")
    Observable<SeatingData> getSeatingWithoutId(@Query("api_token") String token, @Header("lang") String lang);

    //Payment_summary
    @POST("Payment_summary")
    Observable<PaymentSummry> getPaySummary(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);


    //payment_Receipt
    @GET("payment_Receipt")
    Observable<PaymentMoreData> getPayData(@Query("api_token") String token, @Header("lang") String lang);

    //social media
    @GET("Social_Media_Links")
    Observable<SocialMediaData> getSocial(@Query("api_token") String token, @Header("lang") String lang);

    @POST("rest_menu")
    Observable<RestMenu> getMenuRest(@Query("api_token") String token, @Header("lang") String lang, @Body RequestBody body);
}
