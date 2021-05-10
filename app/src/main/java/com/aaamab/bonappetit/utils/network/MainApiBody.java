package com.aaamab.bonappetit.utils.network;

import android.util.Log;

import androidx.annotation.NonNull;


import com.aaamab.bonappetit.utils.StaticMethods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.content.ContentValues.TAG;

public class MainApiBody {
    private static final String JSON_TYPE = "application/json";

    @NonNull
    private static RequestBody requestBody(JSONObject jsonBody) {
        return RequestBody.create(MediaType.parse(JSON_TYPE), jsonBody.toString());
    }

    public static RequestBody loginBody(String email, String password) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("email", email);
        params.put("password", password);
        return requestBody(params);
    }


    public static RequestBody registerBody(String email, String phone,
                                           String password, String name, String password_confirmation, String ge, String address) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("email", email);
        params.put("phone", phone);
        params.put("password", password);
        params.put("name", name);
        params.put("gender", ge);
        params.put("password_confirmation", password_confirmation);
        return requestBody(params);
    }

    public static RequestBody homeBody(String category) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("category", category);

        return requestBody(params);
    }

    public static RequestBody res(int rest_id) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("rest_id", rest_id);

        return requestBody(params);
    }


    /**@Query("rest_id") int rest_id , @Query("Type") String Type*/
    public static RequestBody deleteAllOrder(String type, int rest_id) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("rest_id", rest_id);
        params.put("Type", type);
        return requestBody(params);
    }

    //my order api body
    public static RequestBody myOrderBody(String type, int rest_id) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("rest_id", rest_id);
        params.put("Type", type);
        return requestBody(params);
    }


    public static RequestBody postReview(String review, int rest_id, float rate) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("rest_id", rest_id);
        params.put("content", review);
        params.put("evaluate", rate);
        return requestBody(params);
    }

    public static RequestBody changePas(String old, String newPP, String cofirm) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("old_password", old);
        params.put("new_password", newPP);
        params.put("confirm_password", cofirm);
        return requestBody(params);
    }

    public static RequestBody updateUserData(String name, String email, String phone, String loca, String gender) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("name", name);
        params.put("email", email);
        params.put("phone", phone);
        params.put("address", loca);
        params.put("gender", gender);
        return requestBody(params);
    }

    public static RequestBody addAndRemoveFood(int food_id, int amount) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("food_id", food_id);
        params.put("amount", amount);
        return requestBody(params);
    }
    public static RequestBody addFood(int food_id, int amount ,int rest_id , String Type) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("food_id", food_id);
        params.put("amount", amount);
        params.put("rest_id", rest_id);
        params.put("Type", Type);
        return requestBody(params);
    }

    public static RequestBody applyOrder(int rest_id, String type, String payment) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("rest_id", rest_id);
        params.put("type", type);
        params.put("payment_method", payment);
        //payment_method
        return requestBody(params);
    }

    public static RequestBody contactUS(String content) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("content", content);
        return requestBody(params);
    }

    public static RequestBody makePayment(int id, String count, String date, String time, String paymen) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("restaurant_id", id);
        params.put("count", count);
        params.put("date", date);
        params.put("time", time);
        params.put("payment_method", paymen);
        return requestBody(params);
    }
    public static RequestBody makePayment(int id, String count, String date, String time, String paymen , int seat) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("restaurant_id", id);
        params.put("count", count);
        params.put("date", date);
        params.put("time", time);
        params.put("payment_method", paymen);
        return requestBody(params);
    }
    public static RequestBody makePaymentNew(int id, String count, String date, String time, String paymen , int seat) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("restaurant_id", id);
        params.put("count", count);
        params.put("date", date);
        params.put("time", time);
        params.put("steat_type", seat);
        return requestBody(params);
    }
    public static RequestBody reOrder(int order_id) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("order_id", order_id);

        return requestBody(params);
    }

    public static RequestBody getMall(ArrayList<Integer> id) throws JSONException {
        JSONObject params = new JSONObject();
        for (int i = 0; i < id.size(); i++) {
            params.put("zone_id[" + i + "]", id.get(i));
        }
        /**/

        return requestBody(params);
    }

    public static RequestBody rateBody(float rate) throws JSONException {
        JSONObject params = new JSONObject();

        params.put("evaluate", rate);

        /**/

        return requestBody(params);
    }

    /*public static RequestBody getAreaMall(ArrayList<String> id) throws JSONException {

     *//**//*
        StaticMethods.printJson("ogj" , params);
        return requestBody(params);
    }*/

    public static RequestBody postFilter(ArrayList<Integer> city, ArrayList<Integer> cuis, ArrayList<Integer> area, ArrayList<Integer> mall) throws JSONException {
        JSONObject params = new JSONObject();

        params.put("city_id[0]", city.get(0));


        params.put("cuisines_id[0]", cuis.get(0));


        params.put("zone_id[0]", area.get(0));


        params.put("mall_id[0]", mall.get(0));

        /**/
        StaticMethods.printJson("body", params);
        return requestBody(params);
    }

    public static RequestBody filterBooking(String count, String date, String time ,int steat_type) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("count", count);
        params.put("date", date);
        params.put("time", time);
        params.put("steat_type", steat_type);
        return requestBody(params);
    }

    public static RequestBody seatingBody(int res_id) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("rest_id", res_id);
        return requestBody(params);
    }

    public static RequestBody getPaymentSummary(int rest_id, String type, String payment) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("restaurant_id", rest_id);
        params.put("Type", type);
        params.put("payment_method", payment);
        //payment_method
        return requestBody(params);
    }
///
}