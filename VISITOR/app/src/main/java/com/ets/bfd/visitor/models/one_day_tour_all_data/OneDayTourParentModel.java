package com.ets.bfd.visitor.models.one_day_tour_all_data;

import java.util.List;

public class OneDayTourParentModel {

  List<ChildModel> startingPoints;
  GuardModel guardFee;

  public OneDayTourParentModel() {
  }

  public OneDayTourParentModel(List<ChildModel> startingPoints, GuardModel guardFee) {
    this.startingPoints = startingPoints;
    this.guardFee = guardFee;
  }

  public List<ChildModel> getStartingPoints() {
    return startingPoints;
  }

  public void setStartingPoints(List<ChildModel> startingPoints) {
    this.startingPoints = startingPoints;
  }

  public GuardModel getGuardFee() {
    return guardFee;
  }

  public void setGuardFee(GuardModel guardFee) {
    this.guardFee = guardFee;
  }
}
