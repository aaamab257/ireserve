package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResturantsData {
    @SerializedName("id")
    public int id   ;
    @SerializedName("name")
    public String name  ;
    @SerializedName("name_en")
    public String name_en ;
    @SerializedName("description_en")
    public String description_en ;
    @SerializedName("email")
    public String email ;
    @SerializedName("from_date")
    public String from_date ;
    @SerializedName("to_date")
    public String  to_date;
    @SerializedName("main_image")
    public String  main_image;
    @SerializedName("description")
    public String  description;
    @SerializedName("city_id")
    public int  city_id ;
    @SerializedName("zone_id")
    public int zone_id ;
    @SerializedName("mall_id")
    public int mall_id  ;
    @SerializedName("address")
    public String address ;
    @SerializedName("phone")
    public String  phone;

    @SerializedName("table_type")
    public ArrayList<SeatingArray> seating ;

    @SerializedName("persentage")
    public int  persentage;
    @SerializedName("is_active")
    public String  is_active;
    @SerializedName("is_open")
    public String  is_open;
    @SerializedName("is_new")
    public int  is_new ;
    @SerializedName("joint")
    public String joint ;
    @SerializedName("vip")
    public String vip  ;
    @SerializedName("now_price_booking")
    public int now_price_booking ;
    @SerializedName("dine_in")
    public String  dine_in;

    @SerializedName("pickup")
    public String pickup ;
    @SerializedName("curbside")
    public String  curbside;

    @SerializedName("types")
    ArrayList<TypesItem> types ;

    @SerializedName("images")
    ArrayList<ImagesItem> images ;

    public ArrayList<SeatingArray> getSeating() {
        return seating;
    }

    public void setSeating(ArrayList<SeatingArray> seating) {
        this.seating = seating;
    }

    public ArrayList<ImagesItem> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImagesItem> images) {
        this.images = images;
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

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getZone_id() {
        return zone_id;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }

    public int getMall_id() {
        return mall_id;
    }

    public void setMall_id(int mall_id) {
        this.mall_id = mall_id;
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

    public int getPersentage() {
        return persentage;
    }

    public void setPersentage(int persentage) {
        this.persentage = persentage;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_open() {
        return is_open;
    }

    public void setIs_open(String is_open) {
        this.is_open = is_open;
    }

    public int getIs_new() {
        return is_new;
    }

    public void setIs_new(int is_new) {
        this.is_new = is_new;
    }

    public String getJoint() {
        return joint;
    }

    public void setJoint(String joint) {
        this.joint = joint;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public int getNow_price_booking() {
        return now_price_booking;
    }

    public void setNow_price_booking(int now_price_booking) {
        this.now_price_booking = now_price_booking;
    }

    public String getDine_in() {
        return dine_in;
    }

    public void setDine_in(String dine_in) {
        this.dine_in = dine_in;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getCurbside() {
        return curbside;
    }

    public void setCurbside(String curbside) {
        this.curbside = curbside;
    }

    public ArrayList<TypesItem> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<TypesItem> types) {
        this.types = types;
    }
}
