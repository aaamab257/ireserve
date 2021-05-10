package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class SocialArray {

    @SerializedName("facebook")
    public String facebook ;
    @SerializedName("youtube")
    public String youtube ;
    @SerializedName("insta")
    public String insta ;
    @SerializedName("twiter")
    public String twiter ;
    @SerializedName("watsapp")
    public String watsapp ;

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getTwiter() {
        return twiter;
    }

    public void setTwiter(String twiter) {
        this.twiter = twiter;
    }

    public String getWatsapp() {
        return watsapp;
    }

    public void setWatsapp(String watsapp) {
        this.watsapp = watsapp;
    }
}
