package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SupportArray {

    @SerializedName("data")
    public ArrayList<SupportItem> data ;
    @SerializedName("msg")
    public String msg ;
    @SerializedName("code")
    public int code ;

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

    public ArrayList<SupportItem> getData() {
        return data;
    }

    public void setData(ArrayList<SupportItem> data) {
        this.data = data;
    }
}
