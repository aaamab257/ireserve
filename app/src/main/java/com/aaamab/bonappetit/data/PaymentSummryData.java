package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class PaymentSummryData {
    @SerializedName("services_charge")
    public String services_charge ;
    @SerializedName("subtotal")
    public String subtotal ;
    @SerializedName("total_amount")
    public String total_amount ;

    public String getServices_charge() {
        return services_charge;
    }

    public void setServices_charge(String services_charge) {
        this.services_charge = services_charge;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
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
