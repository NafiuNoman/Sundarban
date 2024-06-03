package com.ets.bfd.visitor.models;

import java.io.Serializable;
import java.util.List;

public class DynamicOneDayTicketModel implements Serializable {
    private String parameter_id;
    private String spot_time_slot;
    private String spot_name_en;
    private String spot_name_bn;
    private String time_slot;
    private String qr_code;
    private List<DynamicOneDayTicketListItemModel> ticketItemList;


    public String getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(String parameter_id) {
        this.parameter_id = parameter_id;
    }

    public String getSpot_time_slot() {
        return spot_time_slot;
    }

    public void setSpot_time_slot(String spot_time_slot) {
        this.spot_time_slot = spot_time_slot;
    }

    public String getSpot_name_en() {
        return spot_name_en;
    }

    public void setSpot_name_en(String spot_name_en) {
        this.spot_name_en = spot_name_en;
    }

    public String getSpot_name_bn() {
        return spot_name_bn;
    }

    public void setSpot_name_bn(String spot_name_bn) {
        this.spot_name_bn = spot_name_bn;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public List<DynamicOneDayTicketListItemModel> getTicketItemList() {
        return ticketItemList;
    }

    public void setTicketItemList(List<DynamicOneDayTicketListItemModel> ticketItemList) {
        this.ticketItemList = ticketItemList;
    }
}
