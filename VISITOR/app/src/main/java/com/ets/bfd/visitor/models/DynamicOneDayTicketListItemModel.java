package com.ets.bfd.visitor.models;

import java.io.Serializable;

public class DynamicOneDayTicketListItemModel implements Serializable {
    private String item_name_en;
    private String item_name_bn;
    private String total_person;
    private String entry_fee;
    private String total_vat;
    private String total_fee;
    private String start_time;
    private String end_time;


    public String getItem_name_en() {
        return item_name_en;
    }

    public void setItem_name_en(String item_name_en) {
        this.item_name_en = item_name_en;
    }

    public String getItem_name_bn() {
        return item_name_bn;
    }

    public void setItem_name_bn(String item_name_bn) {
        this.item_name_bn = item_name_bn;
    }

    public String getTotal_person() {
        return total_person;
    }

    public void setTotal_person(String total_person) {
        this.total_person = total_person;
    }

    public String getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(String entry_fee) {
        this.entry_fee = entry_fee;
    }

    public String getTotal_vat() {
        return total_vat;
    }

    public void setTotal_vat(String total_vat) {
        this.total_vat = total_vat;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
