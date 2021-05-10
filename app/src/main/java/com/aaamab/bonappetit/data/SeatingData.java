package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SeatingData {
    /*
    * "code": 200,
    "msg": "Data Shown successfully ",
    "data":*/
    @SerializedName("code")
    public int code ;
    @SerializedName("msg")
    public String msg ;
    @SerializedName("data")
    public ArrayList<SeatingArray> data ;

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

    public ArrayList<SeatingArray> getData() {
        return data;
    }

    public void setData(ArrayList<SeatingArray> data) {
        this.data = data;
    }
}
