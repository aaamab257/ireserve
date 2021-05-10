package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class UserReviews {
    @SerializedName("image")
    public String image ;
    @SerializedName("name")
    public String name ;
    @SerializedName("created_at")
    public String created_at ;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*"user": {
        "id": 4,
                "name": "user6",
                "email": "user6@gmail.com",
                "password": "$2y$10$yUO54jNgfiJfwNt5ktQff.m21vO0VVTBbdbmvEr5s.gQirwRyGFZy",
                "api_token": "KMRCjgEdoLSWfKWPjLBodMx43k3mXWfqDYpCrssO7qct7RbaI7kyaV6Q70M3",
                "fcm_token": null,
                "image": null,
                "phone": "01201636327",
                "address": null,
                "longitude": null,
                "latitude": null,
                "is_active": 1,
                "gender": "male",
                "is_accept": 1,
                "code": null,
                "tmpApiToken": null,
                "deleted_at": null,
                "created_at": "2021-01-19 13:08:54",
                "updated_at": "2021-02-13 15:16:55"
    }*/
}
