package net.michalfoksa.workshop.station.domain;

import java.util.ArrayList;
import java.util.List;

public class WorkOrder {

    private List<KeyVal> parameters = new ArrayList<>();
    private List<Work> nextStations = new ArrayList<>();

    public List<KeyVal> getParameters() {
        return parameters;
    }

    public void setParameters(List<KeyVal> parameters) {
        this.parameters = parameters;
    }

    public List<Work> getNextStations() {
        return nextStations;
    }

    public void setNextStations(List<Work> nextStations) {
        this.nextStations = nextStations;
    }

    @Override
    public String toString() {
        return "WorkOrder [parameters=" + parameters + ", nextStations=" + nextStations + "]";
    }

}
