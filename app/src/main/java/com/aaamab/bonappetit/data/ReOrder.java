package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class ReOrder {

    @SerializedName("order_id")
    public String msg ;

    @SerializedName("api_token")
    public String code ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
