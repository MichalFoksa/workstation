package net.michalfoksa.demos.workshop.workstation.domain;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Workstation {


    private String name;
    private String url;
    private HashMap<String, String> parameters = new HashMap<>();

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

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public Workstation parameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    @Override
    public String toString() {
        return "Workstation [name=" + name + ", url=" + url + ", parameters=" + parameters + "]";
    }

}

