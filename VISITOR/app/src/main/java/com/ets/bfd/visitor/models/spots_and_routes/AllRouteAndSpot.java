package com.ets.bfd.visitor.models.spots_and_routes;

import java.util.List;

public class AllRouteAndSpot {

    public List<RouteModel> getRoutes;
    public List<OldSpotModel> getSpots;

    public AllRouteAndSpot() {
    }

    public AllRouteAndSpot(List<RouteModel> getRoutes, List<OldSpotModel> getSpots) {
        this.getRoutes = getRoutes;
        this.getSpots = getSpots;
    }

    public List<RouteModel> getGetRoutes() {
        return getRoutes;
    }

    public void setGetRoutes(List<RouteModel> getRoutes) {
        this.getRoutes = getRoutes;
    }

    public List<OldSpotModel> getGetSpots() {
        return getSpots;
    }

    public void setGetSpots(List<OldSpotModel> getSpots) {
        this.getSpots = getSpots;
    }
}
