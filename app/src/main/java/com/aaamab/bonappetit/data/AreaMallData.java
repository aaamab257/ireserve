package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AreaMallData {
    @SerializedName("mall")
    public ArrayList<MallItem> mall ;

    @SerializedName("area")
    public ArrayList<ZoneItem> zone ;

    public ArrayList<MallItem> getMall() {
        return mall;
    }

    public void setMall(ArrayList<MallItem> mall) {
        this.mall = mall;
    }

    public ArrayList<ZoneItem> getZone() {
        return zone;
    }

    public void setZone(ArrayList<ZoneItem> zone) {
        this.zone = zone;
    }
}
