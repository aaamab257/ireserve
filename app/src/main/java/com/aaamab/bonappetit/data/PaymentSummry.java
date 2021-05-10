package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class PaymentSummry {

    @SerializedName("code")
    public int code ;
    @SerializedName("msg")
    public String msg ;
    @SerializedName("data")
    public PaymentSummryData data ;

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

    public PaymentSummryData getData() {
        return data;
    }

    public void setData(PaymentSummryData data) {
        this.data = data;
    }
    /*
    * "code": 200,
    "msg": "admin.Payment_summary",
    "data": {
        "services_charge": 0,
        "subtotal": 0,
        "total_amount": 0
    }*/
}
