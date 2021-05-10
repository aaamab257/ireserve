package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class AddFoodItem {
    @SerializedName("id")
    public  int id ;
    @SerializedName("user_id")
    public  int user_id ;
    @SerializedName("food_id")
    public  int food_id ;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
    * {
            "id": 95,
            "user_id": 24,
            "food_id": 15,
            "price": 5,
            "amount": 1,
            "total": 5,
            "status": "in_process",
            "order_id": null,
            "created_at": "2021-02-27 14:38:25",
            "updated_at": "2021-02-27 14:38:25"
        }
    * */
}
