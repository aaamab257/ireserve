package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookingArray {

    @SerializedName("data")
    public ArrayList<BookingItem<BookingItemOBJ>> bookingItems ;

    @SerializedName("code")
    public int code ;

    @SerializedName("msg")
    public String msg ;

    public ArrayList<BookingItem<BookingItemOBJ>> getBookingItems() {
        return bookingItems;
    }

    public void setBookingItems(ArrayList<BookingItem<BookingItemOBJ>> bookingItems) {
        this.bookingItems = bookingItems;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
