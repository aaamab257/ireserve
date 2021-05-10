package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class MakeBook {
    @SerializedName("status")
    public boolean stu ;
    @SerializedName("data")
    public URLData data ;

    public boolean isStu() {
        return stu;
    }

    public void setStu(boolean stu) {
        this.stu = stu;
    }

    public URLData getData() {
        return data;
    }

    public void setData(URLData data) {
        this.data = data;
    }
}
