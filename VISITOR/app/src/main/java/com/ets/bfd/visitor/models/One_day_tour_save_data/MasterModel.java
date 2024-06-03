package com.ets.bfd.visitor.models.One_day_tour_save_data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MasterModel {

    public String tour_type_id;
    public String applicant_name;
    public String mobile_no;
    public String tour_date;
    public List<String> spot_id;
    public String time_slot_id;
    public List<String> tourist_type_id;
    public List<String> total_person;
    public List<String> vessel_type;
    public String _token;
    @SerializedName("type")
    public List<String> types;
    public List<String> vessel_registration_status;
    public List<String> vessel_id;
    public List<String> vessel_name;
    public List<String> entry_fee;
    public List<String> total_fee;
    public String grand_total;


    public MasterModel() {
    }

    public MasterModel(String tour_type_id, String applicant_name, String mobile_no, String tour_date, List<String> spot_id, String time_slot_id, List<String> tourist_type_id, List<String> total_person, List<String> vessel_type, String _token, List<String> types, List<String> vessel_registration_status, List<String> vessel_id, List<String> vessel_name, List<String> entry_fee, List<String> total_fee, String grand_total) {
        this.tour_type_id = tour_type_id;
        this.applicant_name = applicant_name;
        this.mobile_no = mobile_no;
        this.tour_date = tour_date;
        this.spot_id = spot_id;
        this.time_slot_id = time_slot_id;
        this.tourist_type_id = tourist_type_id;
        this.total_person = total_person;
        this.vessel_type = vessel_type;
        this._token = _token;
        this.types = types;
        this.vessel_registration_status = vessel_registration_status;
        this.vessel_id = vessel_id;
        this.vessel_name = vessel_name;
        this.entry_fee = entry_fee;
        this.total_fee = total_fee;
        this.grand_total = grand_total;
    }

    public String getTour_type_id() {
        return tour_type_id;
    }

    public void setTour_type_id(String tour_type_id) {
        this.tour_type_id = tour_type_id;
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

    public String getTour_date() {
        return tour_date;
    }

    public void setTour_date(String tour_date) {
        this.tour_date = tour_date;
    }

    public List<String> getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(List<String> spot_id) {
        this.spot_id = spot_id;
    }

    public String getTime_slot_id() {
        return time_slot_id;
    }

    public void setTime_slot_id(String time_slot_id) {
        this.time_slot_id = time_slot_id;
    }

    public List<String> getTourist_type_id() {
        return tourist_type_id;
    }

    public void setTourist_type_id(List<String> tourist_type_id) {
        this.tourist_type_id = tourist_type_id;
    }

    public List<String> getTotal_person() {
        return total_person;
    }

    public void setTotal_person(List<String> total_person) {
        this.total_person = total_person;
    }

    public List<String> getVessel_type() {
        return vessel_type;
    }

    public void setVessel_type(List<String> vessel_type) {
        this.vessel_type = vessel_type;
    }

    public String get_token() {
        return _token;
    }

    public void set_token(String _token) {
        this._token = _token;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getVessel_registration_status() {
        return vessel_registration_status;
    }

    public void setVessel_registration_status(List<String> vessel_registration_status) {
        this.vessel_registration_status = vessel_registration_status;
    }

    public List<String> getVessel_id() {
        return vessel_id;
    }

    public void setVessel_id(List<String> vessel_id) {
        this.vessel_id = vessel_id;
    }

    public List<String> getVessel_name() {
        return vessel_name;
    }

    public void setVessel_name(List<String> vessel_name) {
        this.vessel_name = vessel_name;
    }

    public List<String> getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(List<String> entry_fee) {
        this.entry_fee = entry_fee;
    }

    public List<String> getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(List<String> total_fee) {
        this.total_fee = total_fee;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }
}
