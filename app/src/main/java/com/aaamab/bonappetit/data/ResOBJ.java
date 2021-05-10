package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class ResOBJ {
    @SerializedName("id")
    public int id ;
    @SerializedName("name")
    public String name ;
    @SerializedName("name_en")
    public String name_en;

    //main_image
    @SerializedName("main_image")
    public String main_image;

    @SerializedName("restaurant_id")
    public int resId ;

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }


}
