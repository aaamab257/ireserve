package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class URLData {
    @SerializedName("url")
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
