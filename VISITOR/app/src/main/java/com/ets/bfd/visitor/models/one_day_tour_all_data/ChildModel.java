package com.ets.bfd.visitor.models.one_day_tour_all_data;

import java.util.List;

public class ChildModel {

    int id;
    String pointName;
    String pointNameBn;
    List<SpotsModel> spots;

    public ChildModel() {
    }

    public ChildModel(int id, String pointName, String pointNameBn, List<SpotsModel> spots) {
        this.id = id;
        this.pointName = pointName;
        this.pointNameBn = pointNameBn;
        this.spots = spots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPointNameBn() {
        return pointNameBn;
    }

    public void setPointNameBn(String pointNameBn) {
        this.pointNameBn = pointNameBn;
    }

    public List<SpotsModel> getSpots() {
        return spots;
    }

    public void setSpots(List<SpotsModel> spots) {
        this.spots = spots;
    }
}
