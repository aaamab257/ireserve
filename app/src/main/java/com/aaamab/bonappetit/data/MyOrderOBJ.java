package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class MyOrderOBJ {
    @SerializedName("id")
    public int id ;
    @SerializedName("restaurant_id")
    public int restaurant_id ;
    @SerializedName("name")
    public String name ;
    @SerializedName("description")
    public String description ;
    @SerializedName("name_en")
    public String name_en ;
    @SerializedName("description_en")
    public String description_en ;
    @SerializedName("price")
    public String price ;
    @SerializedName("image")
    public String  image ;
    @SerializedName("status")
    public String  status ;
    @SerializedName("is_offer")
    public String  is_offer ;
    @SerializedName("offer_days")
    public int  offer_days ;
    @SerializedName("offer_start")
    public String  offer_start ;
    @SerializedName("offer_end")
    public String  offer_end ;
    @SerializedName("offer_price")
    public String  offer_price ;
    @SerializedName("food_category_id")
    public int  food_category_id ;
    @SerializedName("created_at")
    public String  created_at ;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_offer() {
        return is_offer;
    }

    public void setIs_offer(String is_offer) {
        this.is_offer = is_offer;
    }

    public int getOffer_days() {
        return offer_days;
    }

    public void setOffer_days(int offer_days) {
        this.offer_days = offer_days;
    }

    public String getOffer_start() {
        return offer_start;
    }

    public void setOffer_start(String offer_start) {
        this.offer_start = offer_start;
    }

    public String getOffer_end() {
        return offer_end;
    }

    public void setOffer_end(String offer_end) {
        this.offer_end = offer_end;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }

    public int getFood_category_id() {
        return food_category_id;
    }

    public void setFood_category_id(int food_category_id) {
        this.food_category_id = food_category_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
