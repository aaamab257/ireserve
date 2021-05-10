package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class MakePaymentOBJ {

    @SerializedName("status")
    public boolean status;

    @SerializedName("data")
    public URLData data ;

    @SerializedName("url")
    public String url;

    public URLData getData() {
        return data;
    }

    public void setData(URLData data) {
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
