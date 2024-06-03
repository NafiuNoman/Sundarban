package com.ets.bfd.visitor.models.touristfee;

import java.util.List;

public class TouristModel {

    List<TouristDetails> touristFees;
    GuardPrice guardFee;

    public TouristModel() {
    }

    public TouristModel(List<TouristDetails> touristFees, GuardPrice guardFee) {
        this.touristFees = touristFees;
        this.guardFee = guardFee;
    }

    public List<TouristDetails> getTouristFees() {
        return touristFees;
    }

    public void setTouristFees(List<TouristDetails> touristFees) {
        this.touristFees = touristFees;
    }

    public GuardPrice getGuardFee() {
        return guardFee;
    }

    public void setGuardFee(GuardPrice guardFee) {
        this.guardFee = guardFee;
    }
}
