package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class ProfileDataPra {
    @SerializedName("id")
    public int id ;
    @SerializedName("name")
    public String name ;
    @SerializedName("email")
    public String email ;
    @SerializedName("address")
    public String address ;
    @SerializedName("phone")
    public String phone ;
    @SerializedName("gender")
    public String gender ; //

    @SerializedName("created_at")
    public String created_at ;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
