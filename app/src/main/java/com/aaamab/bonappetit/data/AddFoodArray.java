package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddFoodArray {

    @SerializedName("data")
    public ArrayList<AddFoodItem> addFoodItems ;
    @SerializedName("msg")
    public String msg ;

    public ArrayList<AddFoodItem> getAddFoodItems() {
        return addFoodItems;
    }

    public void setAddFoodItems(ArrayList<AddFoodItem> addFoodItems) {
        this.addFoodItems = addFoodItems;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
