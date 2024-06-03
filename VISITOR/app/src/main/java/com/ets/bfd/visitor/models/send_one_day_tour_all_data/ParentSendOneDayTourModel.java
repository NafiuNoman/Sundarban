package com.ets.bfd.visitor.models.send_one_day_tour_all_data;

import java.io.Serializable;
import java.util.List;

public class ParentSendOneDayTourModel implements Serializable {

    String pickPointId;
    String pickPointName;
    String spotId;
    String spotName;
    String visitDate;
    String timeSlotId;
    String timeSlot;
    String is_vessel;
    String is_guard;
    String is_tourist;

    List<SendOneDayTouristModel> sendTouristList;
    List<SendOneDayVesselModel> sendVesselList;
    String[] passportArray;
    SendOneDayGuardModel sendOneDayGuardModel;
    String totalSingleTicketFee;

    public ParentSendOneDayTourModel() {
    }

    public ParentSendOneDayTourModel(String pickPointId, String pickPointName, String spotId, String spotName, String visitDate, String timeSlotId, String timeSlot, String is_vessel, String is_guard, String is_tourist, List<SendOneDayTouristModel> sendTouristList, List<SendOneDayVesselModel> sendVesselList, String[] passportArray, SendOneDayGuardModel sendOneDayGuardModel, String totalSingleTicketFee) {
        this.pickPointId = pickPointId;
        this.pickPointName = pickPointName;
        this.spotId = spotId;
        this.spotName = spotName;
        this.visitDate = visitDate;
        this.timeSlotId = timeSlotId;
        this.timeSlot = timeSlot;
        this.is_vessel = is_vessel;
        this.is_guard = is_guard;
        this.is_tourist = is_tourist;
        this.sendTouristList = sendTouristList;
        this.sendVesselList = sendVesselList;
        this.passportArray = passportArray;
        this.sendOneDayGuardModel = sendOneDayGuardModel;
        this.totalSingleTicketFee = totalSingleTicketFee;
    }

    public String getPickPointName() {
        return pickPointName;
    }

    public void setPickPointName(String pickPointName) {
        this.pickPointName = pickPointName;
    }

    public String getPickPointId() {
        return pickPointId;
    }

    public void setPickPointId(String pickPointId) {
        this.pickPointId = pickPointId;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(String timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getIs_vessel() {
        return is_vessel;
    }

    public void setIs_vessel(String is_vessel) {
        this.is_vessel = is_vessel;
    }

    public String getIs_guard() {
        return is_guard;
    }

    public void setIs_guard(String is_guard) {
        this.is_guard = is_guard;
    }

    public String getIs_tourist() {
        return is_tourist;
    }

    public void setIs_tourist(String is_tourist) {
        this.is_tourist = is_tourist;
    }

    public List<SendOneDayTouristModel> getSendTouristList() {
        return sendTouristList;
    }

    public void setSendTouristList(List<SendOneDayTouristModel> sendTouristList) {
        this.sendTouristList = sendTouristList;
    }

    public List<SendOneDayVesselModel> getSendVesselList() {
        return sendVesselList;
    }

    public void setSendVesselList(List<SendOneDayVesselModel> sendVesselList) {
        this.sendVesselList = sendVesselList;
    }

    public SendOneDayGuardModel getSendOneDayGuardModel() {
        return sendOneDayGuardModel;
    }

    public void setSendOneDayGuardModel(SendOneDayGuardModel sendOneDayGuardModel) {
        this.sendOneDayGuardModel = sendOneDayGuardModel;
    }

    public String getTotalSingleTicketFee() {
        return totalSingleTicketFee;
    }

    public void setTotalSingleTicketFee(String totalSingleTicketFee) {
        this.totalSingleTicketFee = totalSingleTicketFee;
    }

    public String[] getPassportArray() {
        return passportArray;
    }

    public void setPassportArray(String[] passportArray) {
        this.passportArray = passportArray;
    }
}
