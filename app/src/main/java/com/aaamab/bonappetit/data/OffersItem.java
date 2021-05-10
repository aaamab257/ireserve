package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class OffersItem<T> {

    @SerializedName("id")
    public int id ;

    @SerializedName("rest")
    public T rest ;

    @SerializedName("category")
    public T category ;

    @SerializedName("restaurant_id")
    public int restaurant_id ;
    @SerializedName("price")
    public String price ;
    @SerializedName("offer_price")
    public String offer_price ;
    @SerializedName("offer_days")
    public int days ;

    @SerializedName("image")
    public String img ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getRest() {
        return rest;
    }

    public void setRest(T rest) {
        this.rest = rest;
    }

    public T getCategory() {
        return category;
    }

    public void setCategory(T category) {
        this.category = category;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }



}
