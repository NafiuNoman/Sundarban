package com.ets.bfd.visitor.models.spots_and_routes;

public class RouteModel {

    public int id;
    public String pickup_point_en;
    public String pickup_point_bn;

    public RouteModel() {
    }

    public RouteModel(int id, String pickup_point_en, String pickup_point_bn) {
        this.id = id;
        this.pickup_point_en = pickup_point_en;
        this.pickup_point_bn = pickup_point_bn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPickup_point_en() {
        return pickup_point_en;
    }

    public void setPickup_point_en(String pickup_point_en) {
        this.pickup_point_en = pickup_point_en;
    }

    public String getPickup_point_bn() {
        return pickup_point_bn;
    }

    public void setPickup_point_bn(String pickup_point_bn) {
        this.pickup_point_bn = pickup_point_bn;
    }
}
