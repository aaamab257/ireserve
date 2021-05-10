package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class ImagesItem {

    @SerializedName("id")
    public int id   ;
    @SerializedName("image")
    public String image  ;
    @SerializedName("restaurant_id")
    public int restaurant_id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
