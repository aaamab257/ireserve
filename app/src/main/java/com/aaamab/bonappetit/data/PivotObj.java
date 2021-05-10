package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class PivotObj {

    @SerializedName("type_id")
    public int type_id ;

    @SerializedName("restaurant_id")
    public int restaurant_id ;

    @SerializedName("food_id")
    public int food_id ;

    @SerializedName("order_id")
    public int order_id ;

    @SerializedName("amount")
    public int amount ;

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
}
