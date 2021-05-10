package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class MakeReview {
    @SerializedName("content")
    public String content ;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*{
        "code":200,
            "msg":"Review added successfully",
            "data":{
        "user_id":4,
                "rest_id":"35",
                "content":"very well restsssssss",
                "updated_at":"2021-02-13 17:19:21",
                "created_at":"2021-02-13 17:19:21",
                "id":5
    }
    }*/
}
