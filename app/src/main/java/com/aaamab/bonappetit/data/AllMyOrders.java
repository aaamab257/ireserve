package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllMyOrders {
    @SerializedName("msg")
    public String msg ;
    @SerializedName("code")
    public int code ;
    @SerializedName("data")
    public ArrayList<AllMyOrdersData> data ;

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

    public ArrayList<AllMyOrdersData> getData() {
        return data;
    }

    public void setData(ArrayList<AllMyOrdersData> data) {
        this.data = data;
    }
}
