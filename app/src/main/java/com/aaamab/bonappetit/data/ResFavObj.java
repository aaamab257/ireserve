package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResFavObj {
    @SerializedName("id")
    public int id ;


    @SerializedName("name")
    public String name ;

    @SerializedName("name_en")
    public String name_en ;

    @SerializedName("description_en")
    public String description_en ;

    @SerializedName("main_image")
    public String main_image ;

    @SerializedName("description")
    public String description ;

    @SerializedName("types")
    public ArrayList<FavTypesItems> types ;


    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
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

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<FavTypesItems> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<FavTypesItems> types) {
        this.types = types;
    }


}
