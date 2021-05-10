package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PaymentArrayItem {
    @SerializedName("id")
    public int id;
    @SerializedName("restaurant_id")
    public int restaurant_id;
    @SerializedName("category")
    public String category;
    @SerializedName("delivery_price")
    public String delivery_price;
    @SerializedName("reservation_price")
    public String reservation_price;
    @SerializedName("reservation_count")
    public String reservation_count;
    @SerializedName("reservation_time")
    public String reservation_time;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("restaurant")
    public ResOBJ restaurant;
    @SerializedName("type")
    public String type;
    @SerializedName("Subtotal")
    public float Subtotal;
    @SerializedName("Services charge")
    public float Services_charge;
    @SerializedName("Total amount")
    public float Total_amount;

    @SerializedName("orderFood")
    public ArrayList<FoodsItem> orderFood ;

    public void setTotal_amount(float total_amount) {
        Total_amount = total_amount;
    }

    public ArrayList<FoodsItem> getOrderFood() {
        return orderFood;
    }

    public void setOrderFood(ArrayList<FoodsItem> orderFood) {
        this.orderFood = orderFood;
    }

    public float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(float subtotal) {
        Subtotal = subtotal;
    }

    public float getServices_charge() {
        return Services_charge;
    }

    public void setServices_charge(float services_charge) {
        Services_charge = services_charge;
    }

    public float getTotal_amount() {
        return Total_amount;
    }

    public void setTotal_amount(int total_amount) {
        Total_amount = total_amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDelivery_price() {
        return delivery_price;
    }

    public void setDelivery_price(String delivery_price) {
        this.delivery_price = delivery_price;
    }

    public String getReservation_price() {
        return reservation_price;
    }

    public void setReservation_price(String reservation_price) {
        this.reservation_price = reservation_price;
    }

    public String getReservation_count() {
        return reservation_count;
    }

    public void setReservation_count(String reservation_count) {
        this.reservation_count = reservation_count;
    }

    public String getReservation_time() {
        return reservation_time;
    }

    public void setReservation_time(String reservation_time) {
        this.reservation_time = reservation_time;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public ResOBJ getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(ResOBJ restaurant) {
        this.restaurant = restaurant;
    }
}
