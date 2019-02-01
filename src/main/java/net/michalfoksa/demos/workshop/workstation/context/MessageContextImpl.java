package net.michalfoksa.demos.workshop.workstation.context;

public class MessageContextImpl implements MessageContext {

    private String correlationId;

    @Override
    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public MessageContext correlationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @Override
    public String toString() {
        return "MessageContextImpl [correlationId=" + correlationId + "]";
    }

}
