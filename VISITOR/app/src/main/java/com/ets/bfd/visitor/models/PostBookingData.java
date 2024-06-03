package com.ets.bfd.visitor.models;

public class PostBookingData {

    private String visitor_id;
    private String visitor_name;
    private String visitor_mobile;
    private String visitor_date;
    private String number_of_child;
    private String number_of_adult;
    private String package_id;
    private String code;
    private String message;


    public String getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(String visitor_id) {
        this.visitor_id = visitor_id;
    }

    public String getVisitor_name() {
        return visitor_name;
    }

    public void setVisitor_name(String visitor_name) {
        this.visitor_name = visitor_name;
    }

    public String getVisitor_mobile() {
        return visitor_mobile;
    }

    public void setVisitor_mobile(String visitor_mobile) {
        this.visitor_mobile = visitor_mobile;
    }

    public String getVisitor_date() {
        return visitor_date;
    }

    public void setVisitor_date(String visitor_date) {
        this.visitor_date = visitor_date;
    }

    public String getNumber_of_child() {
        return number_of_child;
    }

    public void setNumber_of_child(String number_of_child) {
        this.number_of_child = number_of_child;
    }

    public String getNumber_of_adult() {
        return number_of_adult;
    }

    public void setNumber_of_adult(String number_of_adult) {
        this.number_of_adult = number_of_adult;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
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
}
