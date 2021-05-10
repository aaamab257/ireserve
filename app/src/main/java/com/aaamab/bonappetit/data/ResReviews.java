package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class ResReviews<T>{
    @SerializedName("user")
    public T user ;
    @SerializedName("id")
    public int id ;
    @SerializedName("user_id")
    public int user_id ;
    @SerializedName("rest_id")
    public int rest_id ;
    @SerializedName("content")
    public String content ; //created_at
    @SerializedName("created_at")
    public String created_at ;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
