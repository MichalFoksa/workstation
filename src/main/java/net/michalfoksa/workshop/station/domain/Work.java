package net.michalfoksa.workshop.station.domain;

import java.util.ArrayList;
import java.util.List;

public class Work {

    private String stationUrl;
    private List<KeyVal> parameters = new ArrayList<>();

    public String getStationUrl() {
        return stationUrl;
    }

    public void setStationUrl(String stationUrl) {
        this.stationUrl = stationUrl;
    }

    public List<KeyVal> getParameters() {
        return parameters;
    }

    public void setParameters(List<KeyVal> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Work [stationUrl=" + stationUrl + ", parameters=" + parameters + "]";
    }

}

