package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class ContactUsObj {


    @SerializedName("content")
    public String content ;
    @SerializedName("user_id")
    public int user_id ;
    @SerializedName("updated_at")
    public String updated_at ;
    @SerializedName("created_at")
    public String created_at ;
    @SerializedName("id")
    public int id ;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

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
}
