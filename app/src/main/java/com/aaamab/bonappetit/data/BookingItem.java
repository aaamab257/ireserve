package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class BookingItem<T> {

    @SerializedName("id")
    public int id ;
    @SerializedName("restaurant_id")
    public int restaurant_id ;
    @SerializedName("table_id")
    public int table_id ;
    @SerializedName("count")
    public int count ;
    @SerializedName("price")
    public int price ;
    @SerializedName("date")
    public String date ;
    @SerializedName("created_at")
    public String created_at ;
    @SerializedName("restaurant")
    public T restaurant ;

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

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public T getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(T restaurant) {
        this.restaurant = restaurant;
    }
    /*
    * {
            "id": 67,
            "restaurant_id": 35,
            "table_id": 15,
            "user_id": 24,
            "count": 2,
            "price": 20,
            "date": "2021-02-28",
            "time": "10:44:00",
            "started_date": "2021-02-28 10:44:00",
            "auto_end_data": "2021-02-28 10:59:00",
            "status": "new",
            "time_start": null,
            "time_end": null,
            "payed": "0",
            "is_accept": 0,
            "is_cancel_by_user": 0,
            "is_cancel_by_restaurant": 0,
            "created_at": "2021-02-28 09:45:02",
            "restaurant": OBJ
        },*/
}
