package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("code")
    public int code ;
    @SerializedName("msg")
    public String msg ;
    @SerializedName("data")
    public RatingStatus data ;

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

    public RatingStatus getData() {
        return data;
    }

    public void setData(RatingStatus data) {
        this.data = data;
    }
}
