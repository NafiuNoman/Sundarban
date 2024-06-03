package com.ets.bfd.visitor.models.spots_and_routes;

public class OldSpotModel {

    public String name_en;
    public Object name_bn;
    public int id;

    public OldSpotModel() {
    }

    public OldSpotModel(String name_en, Object name_bn, int id) {
        this.name_en = name_en;
        this.name_bn = name_bn;
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public Object getName_bn() {
        return name_bn;
    }

    public void setName_bn(Object name_bn) {
        this.name_bn = name_bn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
