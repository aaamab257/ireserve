package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class ZoneItem {
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
    @SerializedName("city_id")
    public int city_id ;

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

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }
    /*{
        "id": 2,
            "name_ar": "الفروانية",
            "name_en": "Farwaniyah",
            "active": "1",
            "city_id": 1,
            "created_at": null,
            "updated_at": "2021-02-03 23:07:16"
    }*/
}
