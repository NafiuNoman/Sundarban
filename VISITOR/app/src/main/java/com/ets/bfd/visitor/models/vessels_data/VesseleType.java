package com.ets.bfd.visitor.models.vessels_data;

public class VesseleType {

    public String vessel_type_with_entry_fee_en;
    public String vessel_type_with_entry_fee_bn;
    public String vessel_category_id;
    public String vessel_size_id;
    public String vessel_entry_fee;
    public String entry_vat;
    public String total_entry_fee;
    public String total_vat_fee;

    public VesseleType() {
    }

    public VesseleType(String vessel_type_with_entry_fee_en, String vessel_type_with_entry_fee_bn, String vessel_category_id, String vessel_size_id, String vessel_entry_fee, String entry_vat, String total_entry_fee, String total_vat_fee) {
        this.vessel_type_with_entry_fee_en = vessel_type_with_entry_fee_en;
        this.vessel_type_with_entry_fee_bn = vessel_type_with_entry_fee_bn;
        this.vessel_category_id = vessel_category_id;
        this.vessel_size_id = vessel_size_id;
        this.vessel_entry_fee = vessel_entry_fee;
        this.entry_vat = entry_vat;
        this.total_entry_fee = total_entry_fee;
        this.total_vat_fee = total_vat_fee;
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

    public String getVessel_type_with_entry_fee_en() {
        return vessel_type_with_entry_fee_en;
    }

    public void setVessel_type_with_entry_fee_en(String vessel_type_with_entry_fee_en) {
        this.vessel_type_with_entry_fee_en = vessel_type_with_entry_fee_en;
    }

    public String getVessel_type_with_entry_fee_bn() {
        return vessel_type_with_entry_fee_bn;
    }

    public void setVessel_type_with_entry_fee_bn(String vessel_type_with_entry_fee_bn) {
        this.vessel_type_with_entry_fee_bn = vessel_type_with_entry_fee_bn;
    }

    public String getVessel_category_id() {
        return vessel_category_id;
    }

    public void setVessel_category_id(String vessel_category_id) {
        this.vessel_category_id = vessel_category_id;
    }

    public String getVessel_size_id() {
        return vessel_size_id;
    }

    public void setVessel_size_id(String vessel_size_id) {
        this.vessel_size_id = vessel_size_id;
    }

    public String getVessel_entry_fee() {
        return vessel_entry_fee;
    }

    public void setVessel_entry_fee(String vessel_entry_fee) {
        this.vessel_entry_fee = vessel_entry_fee;
    }

    public String getEntry_vat() {
        return entry_vat;
    }

    public void setEntry_vat(String entry_vat) {
        this.entry_vat = entry_vat;
    }
}
