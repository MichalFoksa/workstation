package net.michalfoksa.demos.workshop.workstation.context;

public class MessageContextImpl implements MessageContext {

    private String correlationId;
    private boolean returnContexts = true;

    @Override
    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public MessageContextImpl correlationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @Override
    public boolean isReturnContexts() {
        return this.returnContexts;
    }

    public void setReturnContexts(boolean returnContexts) {
        this.returnContexts = returnContexts;
    }

    public MessageContextImpl returnContexts(boolean returnContexts) {
        this.returnContexts = returnContexts;
        return this;
    }

    @Override
    public String toString() {
        return "MessageContextImpl [correlationId=" + correlationId + ", returnContexts=" + returnContexts + "]";
    }

}
