package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class DataObj<T> {
    @SerializedName("data")
    public T data ;

    @SerializedName("msg")
    public String msg ;

    @SerializedName("code")
    public int code ;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
