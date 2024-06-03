package com.ets.bfd.visitor.models.one_day_tour_all_data;

public class TouristTypeModel {

    int id;
    String tourist_type_with_fee;
    String tourist_type_with_fee_bn;
    String type;
    String typeBn;
    String netPrice;
    String vat;
    String totalPrice;


    public TouristTypeModel() {
    }

    public TouristTypeModel(int id, String tourist_type_with_fee, String tourist_type_with_fee_bn, String type, String typeBn, String netPrice, String vat, String totalPrice) {
        this.id = id;
        this.tourist_type_with_fee = tourist_type_with_fee;
        this.tourist_type_with_fee_bn = tourist_type_with_fee_bn;
        this.type = type;
        this.typeBn = typeBn;
        this.netPrice = netPrice;
        this.vat = vat;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTourist_type_with_fee() {
        return tourist_type_with_fee;
    }

    public void setTourist_type_with_fee(String tourist_type_with_fee) {
        this.tourist_type_with_fee = tourist_type_with_fee;
    }

    public String getTourist_type_with_fee_bn() {
        return tourist_type_with_fee_bn;
    }

    public void setTourist_type_with_fee_bn(String tourist_type_with_fee_bn) {
        this.tourist_type_with_fee_bn = tourist_type_with_fee_bn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeBn() {
        return typeBn;
    }

    public void setTypeBn(String typeBn) {
        this.typeBn = typeBn;
    }

    public String getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(String netPrice) {
        this.netPrice = netPrice;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}


