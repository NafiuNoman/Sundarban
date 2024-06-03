package com.ets.bfd.visitor.models;

public class DistrictValue {

    private String name_en;
    private String name_bn;
    private int id;

    public DistrictValue() {
    }

    public DistrictValue(String name_en, String name_bn, int id) {
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

    public String getName_bn() {
        return name_bn;
    }

    public void setName_bn(String name_bn) {
        this.name_bn = name_bn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
