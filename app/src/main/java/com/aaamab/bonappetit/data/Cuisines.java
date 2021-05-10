package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class Cuisines {


    @SerializedName("id")
    public int id ;

    @SerializedName("name_ar")
    public String name_ar ;
    @SerializedName("name")
    public String name_en ;
    @SerializedName("active")
    public String active ;
    @SerializedName("created_at")
    public String created_at ;
    @SerializedName("description_en")
    public String description_en ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_ar() {
        return name_ar;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    /*{
        "id": 1,
            "name": "سوري",
            "description": "المطعم يحتوي علي اكل سوري ممتاز جدا",
            "name_en": "Syrain",
            "description_en": "resuaraent contain perfect food",
            "created_at": "2021-02-07 11:15:44",
            "updated_at": "2021-02-07 11:15:44"
    },*/
}
