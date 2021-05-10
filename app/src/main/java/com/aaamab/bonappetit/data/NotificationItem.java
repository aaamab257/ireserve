package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class NotificationItem {
    @SerializedName("id")
    public int id;
    @SerializedName("message_en")
    public String message_en;
    @SerializedName("user_id")
    public int user_id;
    @SerializedName("title")
    public String title;
    @SerializedName("message")
    public String message;
    @SerializedName("title_en")
    public String title_en;
    @SerializedName("created")
    public String created_at;

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

    public String getMessage_en() {
        return message_en;
    }

    public void setMessage_en(String message_en) {
        this.message_en = message_en;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }
    /*
    * {
            "id": 53,
            "user_id": 24,
            "readed": "",
            "title": "You removed Lentil Soup",
            "title_en": "You removed Lentil Soup",
            "message_en": "You removed Soup",
            "message": "You removed Soup",
            "created_at": "2021-02-27 19:24:56",
            "updated_at": "2021-02-27 19:24:56"
        },*/
}
