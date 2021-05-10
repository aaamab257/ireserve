package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class ProfileData<T> {
    @SerializedName("user_data")
    public T userData ;
    @SerializedName("reservation_number")
    public int reservation_number ;
    @SerializedName("reviews_number")
    public int reviews_number ;
    @SerializedName("created_at")
    public String created_at ;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }



    public int getReservation_number() {
        return reservation_number;
    }

    public void setReservation_number(int reservation_number) {
        this.reservation_number = reservation_number;
    }

    public int getReviews_number() {
        return reviews_number;
    }

    public void setReviews_number(int reviews_number) {
        this.reviews_number = reviews_number;
    }
}
