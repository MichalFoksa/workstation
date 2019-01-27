package net.michalfoksa.workshop.workstation.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkOrder {

    private String workstationName;
    private HashMap<String, String> parameters = new HashMap<>();
    private List<Workstation> nextStations = new ArrayList<>();

    public String getWorkstationName() {
        return workstationName;
    }

    public void setWorkstationName(String workstationName) {
        this.workstationName = workstationName;
    }

    public WorkOrder workstationName(String workstationName) {
        this.workstationName = workstationName;
        return this;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public WorkOrder parameters(HashMap<String, String> parameters) {
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
        return "WorkOrder [workstationName=" + workstationName + ", parameters=" + parameters + ", nextStations="
                + nextStations + "]";
    }

}
