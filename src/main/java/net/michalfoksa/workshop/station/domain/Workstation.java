package net.michalfoksa.workshop.station.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Workstation {

    private String name;
    private String url;
    private List<KeyVal> parameters = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workstation name(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<KeyVal> getParameters() {
        return parameters;
    }

    public void setParameters(List<KeyVal> parameters) {
        this.parameters = parameters;
    }

    public Workstation parameters(List<KeyVal> parameters) {
        this.parameters = parameters;
        return this;
    }

    @Override
    public String toString() {
        return "Workstation [name=" + name + ", url=" + url + ", parameters=" + parameters + "]";
    }

}

