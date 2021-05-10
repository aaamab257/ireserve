package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class FilterCity {
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
    @SerializedName("updated_at")
    public String updated_at ;

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

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }


}
