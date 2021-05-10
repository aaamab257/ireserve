package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class SeatingArray {
    @SerializedName("id")
    public int id ;
    @SerializedName("restaurant_id")
    public int restaurant_id ;
    @SerializedName("name")
    public String name ;
    @SerializedName("name_en")
    public String name_en ;
    @SerializedName("created_at")
    public String created_at ;

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

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    /*
    * {
            "id": 13,
            "restaurant_id": 35,
            "name": "داخلية",
            "name_en": "Indoor",
            "created_at": "2021-03-21 09:32:15"
        },*/
}
