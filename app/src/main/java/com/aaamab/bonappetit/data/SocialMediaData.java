package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SocialMediaData {

    @SerializedName("code")
    public int code ;
    @SerializedName("msg")
    public String msg ;
    @SerializedName("data")
    public ArrayList<SocialArray> data ;

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

    public ArrayList<SocialArray> getData() {
        return data;
    }

    public void setData(ArrayList<SocialArray> data) {
        this.data = data;
    }
}
