package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class SupportItem {
    @SerializedName("Q")
    public String q ;
    @SerializedName("A")
    public String a ;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
