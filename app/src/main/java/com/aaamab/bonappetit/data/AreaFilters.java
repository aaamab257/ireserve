package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class AreaFilters {
    @SerializedName("msg")
    public String msg ;

    @SerializedName("code")
    public int code ;

    @SerializedName("data")
    public FilterObj data ;


    public FilterObj getData() {
        return data;
    }

    public void setData(FilterObj data) {
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
