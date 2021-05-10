package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class RegisterData  {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("api_token")
    public String api_token;

    @SerializedName("phone")
    public String phone;
    @SerializedName("address")
    public String address ;
    @SerializedName("is_active")
    public int is_active ;
    @SerializedName("gender")
    public String gender ;

    @SerializedName("is_accept")
    public int is_accept;
    @SerializedName("code")
    public String code;
}
