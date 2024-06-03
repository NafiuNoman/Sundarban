package com.ets.bfd.visitor.models.send_one_day_tour_all_data;

import java.io.Serializable;
import java.util.List;

public class MainSendModel implements Serializable {

    String name;
    String phoneNumber;
    String visitDate;
    String code;
    String message;
    String returnId;
    String ekpay_secure_token;
    String external_user_type;
    String external_user_id;
    String total_amount;
    String total_revenue;
    String total_vat;
    String startingPointId;
    String pickPointName;


    List<ParentSendOneDayTourModel> parentSendOneDayTourModelList;

    public MainSendModel(String name, String phoneNumber, String visitDate, String code, String message, String returnId, String ekpay_secure_token, String external_user_type, String external_user_id, String total_amount, String total_revenue, String total_vat, String startingPointId, String pickPointName, List<ParentSendOneDayTourModel> parentSendOneDayTourModelList) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.visitDate = visitDate;
        this.code = code;
        this.message = message;
        this.returnId = returnId;
        this.ekpay_secure_token = ekpay_secure_token;
        this.external_user_type = external_user_type;
        this.external_user_id = external_user_id;
        this.total_amount = total_amount;
        this.total_revenue = total_revenue;
        this.total_vat = total_vat;
        this.startingPointId = startingPointId;
        this.pickPointName = pickPointName;
        this.parentSendOneDayTourModelList = parentSendOneDayTourModelList;

    }

    public MainSendModel() {
    }

    public String getPickPointName() {
        return pickPointName;
    }

    public void setPickPointName(String pickPointName) {
        this.pickPointName = pickPointName;
    }

    public String getStartingPointId() {
        return startingPointId;
    }

    public void setStartingPointId(String startingPointId) {
        this.startingPointId = startingPointId;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTotal_revenue() {
        return total_revenue;
    }

    public void setTotal_revenue(String total_revenue) {
        this.total_revenue = total_revenue;
    }

    public String getTotal_vat() {
        return total_vat;
    }

    public void setTotal_vat(String total_vat) {
        this.total_vat = total_vat;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getEkpay_secure_token() {
        return ekpay_secure_token;
    }

    public String getExternal_user_type() {
        return external_user_type;
    }

    public void setExternal_user_type(String external_user_type) {
        this.external_user_type = external_user_type;
    }

    public String getExternal_user_id() {
        return external_user_id;
    }

    public void setExternal_user_id(String external_user_id) {
        this.external_user_id = external_user_id;
    }

    public void setEkpay_secure_token(String ekpay_secure_token) {
        this.ekpay_secure_token = ekpay_secure_token;
    }

    public List<ParentSendOneDayTourModel> getParentSendOneDayTourModelList() {
        return parentSendOneDayTourModelList;
    }

    public void setParentSendOneDayTourModelList(List<ParentSendOneDayTourModel> parentSendOneDayTourModelList) {
        this.parentSendOneDayTourModelList = parentSendOneDayTourModelList;
    }


}
