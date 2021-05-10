package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class RemoveFood<T> {
    @SerializedName("status")
    public boolean status ;
    @SerializedName("data")
    public T da ;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
