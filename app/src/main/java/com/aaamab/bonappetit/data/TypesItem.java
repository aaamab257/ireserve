package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class TypesItem{
    @SerializedName("id")
    public int id   ;
    @SerializedName("name")
    public String name  ;
    @SerializedName("name_en")
    public String name_en ;
    @SerializedName("description_en")
    public String description_en ;
    @SerializedName("description")
    public String description ;

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

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
