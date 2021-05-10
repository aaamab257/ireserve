package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResItem<T> {
    @SerializedName("is_new")
    public String is_new;
    @SerializedName("joint")
    public String joint;
    @SerializedName("vip")
    public String vip;
    @SerializedName("now_price_booking")
    public int now_price_booking;
    @SerializedName("dine_in")
    public String dine_in;
    @SerializedName("pickup")
    public String pickup;
    @SerializedName("curbside")
    public String curbside;
    @SerializedName("id")
    public int id;
    @SerializedName("is_open")
    public String is_open;
    @SerializedName("is_active")
    public String is_active;
    @SerializedName("persentage")
    public int persentage;
    @SerializedName("phone")
    public String phone;
    @SerializedName("longitude")
    public double longitude;
    @SerializedName("latitude")
    public double latitude;
    @SerializedName("address")
    public String address;
    @SerializedName("mall_id")
    public int mall_id;
    @SerializedName("zone_id")
    public int zone_id;
    @SerializedName("city_id")
    public int city_id;
    @SerializedName("description")
    public String description;
    @SerializedName("main_image")
    public String main_image;
    @SerializedName("to_date")
    public String to_date;
    @SerializedName("from_date")
    public String from_date;
    @SerializedName("email")
    public String email;
    @SerializedName("description_en")
    public String description_en;
    @SerializedName("name_en")
    public String name_en;
    @SerializedName("name")
    public String name;
    @SerializedName("reviews")
    ArrayList<ResReviews<UserReviews>> reviews;
    @SerializedName("types")
    ArrayList<TypesItem> typesItems;

    @SerializedName("images")
    ArrayList<ImagesItem> images;
    @SerializedName("food")
    ArrayList<FoodsItem> food;
    @SerializedName("city")
    public T city;

    @SerializedName("zone")
    public T zone;
    @SerializedName("mall")
    public T mall;

    @SerializedName("is_favorit")
    public int is_favorit ;

    public int getIs_favorit() {
        return is_favorit;
    }

    public void setIs_favorit(int is_favorit) {
        this.is_favorit = is_favorit;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIs_open() {
        return is_open;
    }

    public void setIs_open(String is_open) {
        this.is_open = is_open;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public int getPersentage() {
        return persentage;
    }

    public void setPersentage(int persentage) {
        this.persentage = persentage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMall_id() {
        return mall_id;
    }

    public void setMall_id(int mall_id) {
        this.mall_id = mall_id;
    }

    public int getZone_id() {
        return zone_id;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ResReviews<UserReviews>> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ResReviews<UserReviews>> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<TypesItem> getTypesItems() {
        return typesItems;
    }

    public void setTypesItems(ArrayList<TypesItem> typesItems) {
        this.typesItems = typesItems;
    }

    public ArrayList<ImagesItem> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImagesItem> images) {
        this.images = images;
    }

    public ArrayList<FoodsItem> getFood() {
        return food;
    }

    public void setFood(ArrayList<FoodsItem> food) {
        this.food = food;
    }

    public T getCity() {
        return city;
    }

    public void setCity(T city) {
        this.city = city;
    }

    public T getZone() {
        return zone;
    }

    public void setZone(T zone) {
        this.zone = zone;
    }

    public T getMall() {
        return mall;
    }

    public void setMall(T mall) {
        this.mall = mall;
    }
}
