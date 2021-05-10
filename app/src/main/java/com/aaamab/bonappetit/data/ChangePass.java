package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

public class ChangePass {
    @SerializedName("status")
    public boolean status ;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
