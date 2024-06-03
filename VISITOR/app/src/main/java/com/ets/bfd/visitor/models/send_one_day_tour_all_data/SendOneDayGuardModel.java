package com.ets.bfd.visitor.models.send_one_day_tour_all_data;

import java.io.Serializable;

public class SendOneDayGuardModel implements Serializable {

    String guardPerson;
    String netFee;
    String vat;
    String totalFee;

    public SendOneDayGuardModel(String guardPerson, String netFee, String vat, String totalFee) {
        this.guardPerson = guardPerson;
        this.netFee = netFee;
        this.vat = vat;
        this.totalFee = totalFee;
    }

    public SendOneDayGuardModel() {
    }

    public String getGuardPerson() {
        return guardPerson;
    }

    public void setGuardPerson(String guardPerson) {
        this.guardPerson = guardPerson;
    }

    public String getNetFee() {
        return netFee;
    }

    public void setNetFee(String netFee) {
        this.netFee = netFee;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }
}
