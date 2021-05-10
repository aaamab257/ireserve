package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class UpCommingItem {

    @SerializedName("updated_at")
    public String updated_at ;
    @SerializedName("created_at")
    public String created_at ;
    @SerializedName("name")
    public String name_en ;
    @SerializedName("main_image")
    public String main_image ;
    @SerializedName("upcoming")
    public String upcoming ;

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(String upcoming) {
        this.upcoming = upcoming;
    }

    /*{
        "updated_at": "2021-03-21 09:55:22",
            "created_at": null,
            "name_en": "Mais Alghanim",
            "main_image": "https://ot.4salekuwait.com/upload/restaurant/EncJwR_1613897446.jpg",
            "upcoming": "Sun:2021:Mar"
    }*/

}
