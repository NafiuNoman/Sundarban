package com.ets.bfd.visitor.models.send_one_day_tour_all_data;

import java.io.Serializable;

public class SendOneDayTouristModel implements Serializable {

    String touristId;
    String touristName;
    String netPrice;
    String vatPrice;
    String totalPerson;
    String revenue;
    String total;

    public SendOneDayTouristModel(String touristId, String touristName, String netPrice, String vatPrice, String totalPerson, String revenue, String total) {
        this.touristId = touristId;
        this.touristName = touristName;
        this.netPrice = netPrice;
        this.vatPrice = vatPrice;
        this.totalPerson = totalPerson;
        this.revenue = revenue;
        this.total = total;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public String getTotalPerson() {
        return totalPerson;
    }

    public void setTotalPerson(String totalPerson) {
        this.totalPerson = totalPerson;
    }

    public SendOneDayTouristModel() {
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(String netPrice) {
        this.netPrice = netPrice;
    }

    public String getVatPrice() {
        return vatPrice;
    }

    public void setVatPrice(String vatPrice) {
        this.vatPrice = vatPrice;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
