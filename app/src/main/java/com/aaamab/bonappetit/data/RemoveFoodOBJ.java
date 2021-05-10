package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class RemoveFoodOBJ {
    @SerializedName("status")
    public boolean status ;
    @SerializedName("url")
    public String url ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
