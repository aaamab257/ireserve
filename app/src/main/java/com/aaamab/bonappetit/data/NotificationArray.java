package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NotificationArray {
    @SerializedName("data")
    public ArrayList<NotificationItem> notificationItems;
    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;

    public ArrayList<NotificationItem> getNotificationItems() {
        return notificationItems;
    }

    public void setNotificationItems(ArrayList<NotificationItem> notificationItems) {
        this.notificationItems = notificationItems;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
