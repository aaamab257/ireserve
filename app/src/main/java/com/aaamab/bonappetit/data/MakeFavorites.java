package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class MakeFavorites {
    @SerializedName("rest_id")
    public String rest_id ;

    @SerializedName("user_id")
    public int user_id ;

    @SerializedName("id")
    public int id ;

    @SerializedName("status")
    public boolean status ;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRest_id() {
        return rest_id;
    }

    public void setRest_id(String rest_id) {
        this.rest_id = rest_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
