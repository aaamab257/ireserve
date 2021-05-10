package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RestruantByID {
    @SerializedName("code")
    public int code ;
    @SerializedName("msg")
    public String msg ;
    @SerializedName("data")
    ArrayList<ResItem<CityItem>> res ;

    public ArrayList<ResItem<CityItem>> getRes() {
        return res;
    }

    public void setRes(ArrayList<ResItem<CityItem>> res) {
        this.res = res;
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
