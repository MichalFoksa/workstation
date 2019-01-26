package net.michalfoksa.workshop.workstation.context;

public class CallContextImpl implements CallContext {

    private String correlationId;

    @Override
    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public CallContext correlationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @Override
    public String toString() {
        return "CallContext [correlationId=" + correlationId + "]";
    }

}
