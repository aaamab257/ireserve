package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class MyOrders<T> {
    @SerializedName("id")
    public int id ;
    @SerializedName("user_id")
    public int user_id ;
    @SerializedName("food_id")
    public int food_id ;
    @SerializedName("price")
    public double price ;
    @SerializedName("amount")
    public int amount ;
    @SerializedName("total")
    public double total ;
    @SerializedName("created_at")
    public String created_at ;
    @SerializedName("foods")
    public T food ;

    public T getFood() {
        return food;
    }

    public void setFood(T food) {
        this.food = food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }



    /*{
        "id": 19,
            "user_id": 4,
            "food_id": 14,
            "price": 2,
            "amount": 20,
            "total": 40,
            "status": "in_process",
            "order_id": null,
            "created_at": "2021-02-08 03:26:08",
            "updated_at": "2021-02-10 17:44:10",
            "foods": {

    },*/

}
