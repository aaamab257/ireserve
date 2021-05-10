package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RestArray {

    @SerializedName("data")
    public ArrayList<ResturantsData> data;
    @SerializedName("msg")
    public String msg;
    @SerializedName("code")
    public int code;

    public ArrayList<ResturantsData> getData() {
        return data;
    }

    public void setData(ArrayList<ResturantsData> data) {
        this.data = data;
    }

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
}
