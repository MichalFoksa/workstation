package net.michalfoksa.workshop.station.domain;

import java.util.ArrayList;
import java.util.List;

public class WorkOrder {

    private List<KeyVal> parameters = new ArrayList<>();
    private List<Workstation> nextStations = new ArrayList<>();

    public List<KeyVal> getParameters() {
        return parameters;
    }

    public void setParameters(List<KeyVal> parameters) {
        this.parameters = parameters;
    }

    public WorkOrder parameters(List<KeyVal> parameters) {
        this.parameters = parameters;
        return this;
    }

    public List<Workstation> getNextStations() {
        return nextStations;
    }

    public void setNextStations(List<Workstation> nextStations) {
        this.nextStations = nextStations;
    }

    public WorkOrder nextStations(List<Workstation> nextStations) {
        this.nextStations = nextStations;
        return this;
    }

    @Override
    public String toString() {
        return "WorkOrder [parameters=" + parameters + ", nextStations=" + nextStations + "]";
    }

}
