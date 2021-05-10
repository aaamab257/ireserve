package com.aaamab.bonappetit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FilterObj {
    @SerializedName("City")
    public ArrayList<FilterCity> city;

    /*@SerializedName("Mall")
    public ArrayList<MallItem> mall;*/

    @SerializedName("Mall")
    public ArrayList<MallItem> mallss;

    @SerializedName("Area")
    public ArrayList<ZoneItem> zone;


    @SerializedName("cuisines")
    public ArrayList<Cuisines> cuisines;


    public ArrayList<MallItem> getMallss() {
        return mallss;
    }

    public void setMallss(ArrayList<MallItem> mallss) {
        this.mallss = mallss;
    }

    public ArrayList<FilterCity> getCity() {
        return city;
    }

    public void setCity(ArrayList<FilterCity> city) {
        this.city = city;
    }

    /*public ArrayList<MallItem> getMall() {
        return mall;
    }

    public void setMall(ArrayList<MallItem> mall) {
        this.mall = mall;
    }*/

    public ArrayList<ZoneItem> getZone() {
        return zone;
    }

    public void setZone(ArrayList<ZoneItem> zone) {
        this.zone = zone;
    }

    public ArrayList<Cuisines> getCuisines() {
        return cuisines;
    }

    public void setCuisines(ArrayList<Cuisines> cuisines) {
        this.cuisines = cuisines;
    }
}
