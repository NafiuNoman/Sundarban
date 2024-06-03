package com.ets.bfd.visitor.models.send_one_day_tour_all_data;

import java.io.Serializable;

public class SendOneDayVesselModel implements Serializable {
    String vessel_id;
    String vessel_category_id;
    String vessel_size_id ;
    String vesselName;
    String entry_fee;
    String total_fee;
    String total_vat;

    String is_bfd_registered;






    public SendOneDayVesselModel() {
    }

    public SendOneDayVesselModel(String is_bfd_registered, String vessel_id, String vesselName, String entry_fee, String total_fee, String total_vat, String vessel_category_id, String vessel_size_id) {
        this.is_bfd_registered = is_bfd_registered;
        this.vessel_id = vessel_id;
        this.vesselName = vesselName;
        this.entry_fee = entry_fee;
        this.total_fee = total_fee;
        this.total_vat = total_vat;
        this.vessel_category_id = vessel_category_id;
        this.vessel_size_id = vessel_size_id;
    }

    public String getIs_bfd_registered() {
        return is_bfd_registered;
    }

    public void setIs_bfd_registered(String is_bfd_registered) {
        this.is_bfd_registered = is_bfd_registered;
    }

    public String getVessel_id() {
        return vessel_id;
    }

    public void setVessel_id(String vessel_id) {
        this.vessel_id = vessel_id;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(String entry_fee) {
        this.entry_fee = entry_fee;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getTotal_vat() {
        return total_vat;
    }

    public void setTotal_vat(String total_vat) {
        this.total_vat = total_vat;
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

}
