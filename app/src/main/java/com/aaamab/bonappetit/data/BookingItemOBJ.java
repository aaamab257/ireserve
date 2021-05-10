package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class BookingItemOBJ {
    @SerializedName("id")
    public int id ;
    @SerializedName("name")
    public String name ;
    @SerializedName("name_en")
    public String name_en ;
    @SerializedName("description_en")
    public String description_en ;
    @SerializedName("email")
    public String email ;
    @SerializedName("main_image")
    public String main_image ;

    //phone
    @SerializedName("phone")
    public String phone ;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    /*{
                "id": 35,
                "name": "Mais Alghanim",
                "name_en": "Mais Alghanim",
                "description_en": "Best Arabic and national Dishes",
                "email": "info@maisalghanim.com",
                "from_date": "2021-02-03",
                "to_date": "2021-03-12",
                "main_image": "https://ot.4salekuwait.com/upload/restaurant/EncJwR_1613897446.jpg",
                "description": "اشهى الأطباق العربية والعالمية",
                "city_id": 1,
                "zone_id": 2,
                "mall_id": 2,
                "address": "Kuwait Kuwait city, 13012, Kuwait",
                "latitude": 29.39073,
                "longitude": 47.99871,
                "phone": "2225 1155",
                "instgram": null,
                "facebook": null,
                "youtube": null,
                "persentage": 10,
                "is_active": "active",
                "is_open": "false",
                "is_new": 0,
                "joint": "y",
                "vip": "1",
                "now_price_booking": 10,
                "dine_in": "1",
                "pickup": "1",
                "curbside": "1",
                "created_at": null,
                "updated_at": "2021-02-22 12:05:05",
                "deleted_at": null
            }*/
}
