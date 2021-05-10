package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TermsArray {
    @SerializedName("data")
    public ArrayList<TermsItem> termsItems ;

    @SerializedName("code")
    public int code ;

    @SerializedName("msg")
    public String msg ;

    public ArrayList<TermsItem> getTermsItems() {
        return termsItems;
    }

    public void setTermsItems(ArrayList<TermsItem> termsItems) {
        this.termsItems = termsItems;
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
