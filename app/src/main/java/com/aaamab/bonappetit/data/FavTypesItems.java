package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class FavTypesItems {
    @SerializedName("id")
    public int id ;
    @SerializedName("name_en")
    public String name_en ;
    @SerializedName("name")
    public String name ;
    @SerializedName("description_en")
    public String description_en ;
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

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
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
