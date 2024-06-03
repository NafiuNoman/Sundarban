package com.ets.bfd.visitor.models.vessels_data;

public class RegisteredVessel {

    public int vessele_id;
    public int vessel_category_id;
    public int vessel_size_id;
    public String vessel_name_en;
    public String vessel_name_bn;
    public String vessel_entry_fee;
    public String vessel_vat_fee;
    public String total_entry_fee;
    public String total_vat_fee;
    public String vessel_number;

    public RegisteredVessel() {
    }

    public RegisteredVessel(int vessele_id, int vessel_category_id, int vessel_size_id, String vessel_name_en, String vessel_name_bn, String vessel_entry_fee, String vessel_vat_fee, String total_entry_fee, String total_vat_fee, String vessel_number) {
        this.vessele_id = vessele_id;
        this.vessel_category_id = vessel_category_id;
        this.vessel_size_id = vessel_size_id;
        this.vessel_name_en = vessel_name_en;
        this.vessel_name_bn = vessel_name_bn;
        this.vessel_entry_fee = vessel_entry_fee;
        this.vessel_vat_fee = vessel_vat_fee;
        this.total_entry_fee = total_entry_fee;
        this.total_vat_fee = total_vat_fee;
        this.vessel_number = vessel_number;
    }

    public String getTotal_entry_fee() {
        return total_entry_fee;
    }

    public void setTotal_entry_fee(String total_entry_fee) {
        this.total_entry_fee = total_entry_fee;
    }

    public String getTotal_vat_fee() {
        return total_vat_fee;
    }

    public void setTotal_vat_fee(String total_vat_fee) {
        this.total_vat_fee = total_vat_fee;
    }

    public int getVessele_id() {
        return vessele_id;
    }

    public void setVessele_id(int vessele_id) {
        this.vessele_id = vessele_id;
    }

    public int getVessel_category_id() {
        return vessel_category_id;
    }

    public void setVessel_category_id(int vessel_category_id) {
        this.vessel_category_id = vessel_category_id;
    }

    public int getVessel_size_id() {
        return vessel_size_id;
    }

    public void setVessel_size_id(int vessel_size_id) {
        this.vessel_size_id = vessel_size_id;
    }

    public String getVessel_name_en() {
        return vessel_name_en;
    }

    public void setVessel_name_en(String vessel_name_en) {
        this.vessel_name_en = vessel_name_en;
    }

    public String getVessel_name_bn() {
        return vessel_name_bn;
    }

    public void setVessel_name_bn(String vessel_name_bn) {
        this.vessel_name_bn = vessel_name_bn;
    }

    public String getVessel_entry_fee() {
        return vessel_entry_fee;
    }

    public void setVessel_entry_fee(String vessel_entry_fee) {
        this.vessel_entry_fee = vessel_entry_fee;
    }

    public String getVessel_vat_fee() {
        return vessel_vat_fee;
    }

    public void setVessel_vat_fee(String vessel_vat_fee) {
        this.vessel_vat_fee = vessel_vat_fee;
    }

    public String getVessel_number() {
        return vessel_number;
    }

    public void setVessel_number(String vessel_number) {
        this.vessel_number = vessel_number;
    }
}
