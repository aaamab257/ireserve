package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllMyOrdersData {
    @SerializedName("id")
    public int id ;
    @SerializedName("restaurant_id")
    public int restaurant_id ;
    @SerializedName("user_id")
    public int user_id ;
    @SerializedName("price")
    public int price ;
    @SerializedName("Address")
    public String Address ;
    @SerializedName("note")
    public String note ;
    @SerializedName("status")
    public String status ;
    @SerializedName("type")
    public String type ;
    @SerializedName("is_cancel")
    public int is_cancel ;
    @SerializedName("is_accept")
    public int is_accept ;
    @SerializedName("created_at")
    public String created_at ;
    @SerializedName("order_food")
    public ArrayList<OrdersFoodItem<PivotObj>> order_food ;

    @SerializedName("restaurant")
    public ResturantsData resData ;

    public ResturantsData getResData() {
        return resData;
    }

    public void setResData(ResturantsData resData) {
        this.resData = resData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIs_cancel() {
        return is_cancel;
    }

    public void setIs_cancel(int is_cancel) {
        this.is_cancel = is_cancel;
    }

    public int getIs_accept() {
        return is_accept;
    }

    public void setIs_accept(int is_accept) {
        this.is_accept = is_accept;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public ArrayList<OrdersFoodItem<PivotObj>> getOrder_food() {
        return order_food;
    }

    public void setOrder_food(ArrayList<OrdersFoodItem<PivotObj>> order_food) {
        this.order_food = order_food;
    }
}
