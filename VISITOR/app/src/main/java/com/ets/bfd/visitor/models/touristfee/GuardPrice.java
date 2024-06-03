package com.ets.bfd.visitor.models.touristfee;

public class GuardPrice {

    String total_fee;

    public GuardPrice(String total_fee) {
        this.total_fee = total_fee;
    }

    public GuardPrice() {
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }
}
