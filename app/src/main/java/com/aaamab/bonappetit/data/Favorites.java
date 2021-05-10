package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class Favorites{
    @SerializedName("id")
    public int id ;
    @SerializedName("rest")
    public ResFavObj  rest ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResFavObj getRest() {
        return rest;
    }

    public void setRest(ResFavObj rest) {
        this.rest = rest;
    }
}
