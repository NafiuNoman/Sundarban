package com.ets.bfd.visitor.dao;

public class ExampleListModel {

    private String spotName;
    private String personType;
    private int headCount;
    private Float perPrice;
    private Float totalPrice;

    public ExampleListModel() {
    }

    public ExampleListModel(String spotName, String touristType, int headCount, Float perPrice, Float totalPrice) {
        this.spotName = spotName;
        this.personType = touristType;
        this.headCount = headCount;
        this.perPrice = perPrice;
        this.totalPrice = totalPrice;
    }


    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String touristType) {
        this.personType = touristType;
    }

    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    public Float getPerPrice() {
        return perPrice;
    }

    public void setPerPrice(Float perPrice) {
        this.perPrice = perPrice;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
