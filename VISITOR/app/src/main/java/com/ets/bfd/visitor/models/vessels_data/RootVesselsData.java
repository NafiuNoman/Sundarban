package com.ets.bfd.visitor.models.vessels_data;

import java.util.List;

public class RootVesselsData {

    List<RegisteredVessel> registeredVessels;
    List<VesseleType> vesseleType;

    public RootVesselsData() {
    }

    public RootVesselsData(List<RegisteredVessel> registeredVessels, List<VesseleType> vesseleType) {
        this.registeredVessels = registeredVessels;
        this.vesseleType = vesseleType;
    }

    public List<RegisteredVessel> getRegisteredVessels() {
        return registeredVessels;
    }

    public void setRegisteredVessels(List<RegisteredVessel> registeredVessels) {
        this.registeredVessels = registeredVessels;
    }

    public List<VesseleType> getVesseleType() {
        return vesseleType;
    }

    public void setVesseleType(List<VesseleType> vesseleType) {
        this.vesseleType = vesseleType;
    }
}

