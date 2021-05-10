package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RestMenuItem {

    @SerializedName("id")
    public int id ;
    @SerializedName("restaurant_id")
    public int restaurant_id ;
    @SerializedName("name")
    public String name ;
    @SerializedName("created_at")
    public String created_at ;

    @SerializedName("foods")
    public ArrayList<FoodsItem> foods ;

    public ArrayList<FoodsItem> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<FoodsItem> foods) {
        this.foods = foods;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /*"id": 19,
            "restaurant_id": 35,
            "name": "Soup",
            "created_at": "2021-02-03 22:23:51",*/
}
