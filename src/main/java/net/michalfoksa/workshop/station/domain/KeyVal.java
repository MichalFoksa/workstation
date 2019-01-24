package net.michalfoksa.workshop.station.domain;

public class KeyVal {

    private String Name;
    private String Value;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "KeyVal [Name=" + Name + ", Value=" + Value + "]";
    }

}
