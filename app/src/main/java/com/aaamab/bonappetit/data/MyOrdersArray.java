package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyOrdersArray {
    @SerializedName("data")
    public ArrayList<MyOrders<MyOrderOBJ>> myOrders ;
    @SerializedName("msg")
    public String msg ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<MyOrders<MyOrderOBJ>> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(ArrayList<MyOrders<MyOrderOBJ>> myOrders) {
        this.myOrders = myOrders;
    }
}
