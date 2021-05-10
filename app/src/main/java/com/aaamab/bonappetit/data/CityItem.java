package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class CityItem {
    @SerializedName("id")
    public int id ;

    @SerializedName("name_ar")
    public String name_ar ;
    @SerializedName("name_en")
    public String name_en ;
    @SerializedName("active")
    public String active ;
    @SerializedName("city_id")
    public int city_id ;
    @SerializedName("zone_id")
    public int zone_id ;

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

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getZone_id() {
        return zone_id;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }


}
