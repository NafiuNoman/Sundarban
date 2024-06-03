package com.ets.bfd.visitor.models;

import java.io.Serializable;

public class MyTicketListModel implements Serializable {

    private String id;
    private String external_user_type;
    private String ext_user_id;
    private String application_date;
    private String application_number;
    private String applicant_name;
    private String mobile_no;
    private String tour_type_id;
    private String tour_date;
    private String application_status;
    private String frontend_user_id;
    private String backend_user_id;
    private String created_at;
    private String updated_at;
    private String email;
    private String transaction_no;
    private String amount_paid;
    private String spots_name_en;
    private String spots_name_bn;
    private String start_time;
    private String end_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternal_user_type() {
        return external_user_type;
    }

    public void setExternal_user_type(String external_user_type) {
        this.external_user_type = external_user_type;
    }

    public String getExt_user_id() {
        return ext_user_id;
    }

    public void setExt_user_id(String ext_user_id) {
        this.ext_user_id = ext_user_id;
    }

    public String getApplication_date() {
        return application_date;
    }

    public void setApplication_date(String application_date) {
        this.application_date = application_date;
    }

    public String getApplication_number() {
        return application_number;
    }

    public void setApplication_number(String application_number) {
        this.application_number = application_number;
    }

    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getTour_type_id() {
        return tour_type_id;
    }

    public void setTour_type_id(String tour_type_id) {
        this.tour_type_id = tour_type_id;
    }

    public String getTour_date() {
        return tour_date;
    }

    public void setTour_date(String tour_date) {
        this.tour_date = tour_date;
    }

    public String getApplication_status() {
        return application_status;
    }

    public void setApplication_status(String application_status) {
        this.application_status = application_status;
    }

    public String getFrontend_user_id() {
        return frontend_user_id;
    }

    public void setFrontend_user_id(String frontend_user_id) {
        this.frontend_user_id = frontend_user_id;
    }

    public String getBackend_user_id() {
        return backend_user_id;
    }

    public void setBackend_user_id(String backend_user_id) {
        this.backend_user_id = backend_user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTransaction_no() {
        return transaction_no;
    }

    public void setTransaction_no(String transaction_no) {
        this.transaction_no = transaction_no;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getSpots_name_en() {
        return spots_name_en;
    }

    public void setSpots_name_en(String spots_name_en) {
        this.spots_name_en = spots_name_en;
    }

    public String getSpots_name_bn() {
        return spots_name_bn;
    }

    public void setSpots_name_bn(String spots_name_bn) {
        this.spots_name_bn = spots_name_bn;
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
