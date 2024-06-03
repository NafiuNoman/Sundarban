package com.ets.bfd.visitor.models.one_day_tour_all_data;

public class GuardModel {

    int id;
    String fee;
    String vat;
    String total_fee;

    public GuardModel() {
    }

    public GuardModel(int id, String fee, String vat, String total_fee) {
        this.id = id;
        this.fee = fee;
        this.vat = vat;
        this.total_fee = total_fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }
}
