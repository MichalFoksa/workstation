package net.michalfoksa.demo.workshop.workstation.context;

public interface MessageContext {

    String getCorrelationId();

    /***
     * When false, do not set message and runtime contexts in response.
     *
     * @return
     */
    boolean isReturnContexts();

}