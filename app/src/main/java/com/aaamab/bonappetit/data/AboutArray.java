package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AboutArray {
    @SerializedName("data")
    public ArrayList<AboutItem> aboutitems ;

    @SerializedName("code")
    public int code ;

    @SerializedName("msg")
    public String msg ;

    public ArrayList<AboutItem> getAboutitems() {
        return aboutitems;
    }

    public void setAboutitems(ArrayList<AboutItem> aboutitems) {
        this.aboutitems = aboutitems;
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
