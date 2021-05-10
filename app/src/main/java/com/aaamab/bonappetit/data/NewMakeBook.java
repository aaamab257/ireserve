package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class NewMakeBook {
    @SerializedName("msg")
    public String msg ;

    @SerializedName("code")
    public int code ;

    @SerializedName("data")
    public RatingStatus data ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RatingStatus getData() {
        return data;
    }

    public void setData(RatingStatus data) {
        this.data = data;
    }
}
