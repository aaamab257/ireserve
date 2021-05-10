package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class OrdersFoodItem<T> {
    @SerializedName("id")
    public int id;
    @SerializedName("restaurant_id")
    public int restaurant_id;
    @SerializedName("name")
    public String name;
    @SerializedName("name_en")
    public String name_en;
    @SerializedName("description_en")
    public String description_en;
    @SerializedName("description")
    public String description;
    @SerializedName("image")
    public String image;
    @SerializedName("status")
    public String status;
    @SerializedName("price")
    public String  price;
    @SerializedName("is_accept")
    public int is_accept;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("pivot")
    public T pivot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIs_accept() {
        return is_accept;
    }

    public void setIs_accept(int is_accept) {
        this.is_accept = is_accept;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public T getPivot() {
        return pivot;
    }

    public void setPivot(T pivot) {
        this.pivot = pivot;
    }


}
