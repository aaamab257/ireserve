package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class AboutItem {
    @SerializedName("id")
    public int id ;
    @SerializedName("phone")
    public String phone ;
    @SerializedName("email")
    public String email ;

    @SerializedName("location")
    public String location_en ;

    @SerializedName("location_ar")
    public String location_ar ;

    @SerializedName("about_us_ar")
    public String about_us_ar ;
    @SerializedName("about_us")
    public String about_us_en ;

    @SerializedName("facebook")
    public String facebook ;

    @SerializedName("youtube")
    public String youtube ;

    @SerializedName("insta")
    public String insta ;
    @SerializedName("twiter")
    public String twiter ;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation_en() {
        return location_en;
    }

    public void setLocation_en(String location_en) {
        this.location_en = location_en;
    }

    public String getLocation_ar() {
        return location_ar;
    }

    public void setLocation_ar(String location_ar) {
        this.location_ar = location_ar;
    }

    public String getAbout_us_ar() {
        return about_us_ar;
    }

    public void setAbout_us_ar(String about_us_ar) {
        this.about_us_ar = about_us_ar;
    }

    public String getAbout_us_en() {
        return about_us_en;
    }

    public void setAbout_us_en(String about_us_en) {
        this.about_us_en = about_us_en;
    }

    /*
            "id": 1,
            "phone": "25851155",
            "email": "info@ireserveapp.com",
            "location_ar": "16 street",
            "location_en": "16 street",
            "about_us_ar": "We love what can happen around the restaurant table. We’ve been committed to empowering that experience. From helping restaurants of all sizes thrive, to enabling diners to find and book the perfect table for every occasion, our story is one of human conn",
            "about_us_en": "We love what can happen around the restaurant table. We’ve been committed to empowering that experience. From helping restaurants of all sizes thrive, to enabling diners to find and book the perfect table for every occasion, our story is one of human conn",
            "policy_term_ar":
            "facebook": "https://www.facebook.com/",
            "youtube": "https://www.youtube.com/",
            "insta": "https://www.instagram.com/",
            "twiter": "https://www.twiter.com/",
            "watsapp": "https://wa.link/twqpdd",
      */
}
