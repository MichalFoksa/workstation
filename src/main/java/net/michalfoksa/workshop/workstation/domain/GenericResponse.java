package net.michalfoksa.workshop.workstation.domain;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.michalfoksa.workshop.workstation.context.CallContext;
import net.michalfoksa.workshop.workstation.context.CallContextImpl;

/***
 * Generic REST response with payload as body and call and runtime contexts.
 *
 * @author Michal Foksa
 *
 * @param <T>
 */
public class GenericResponse<T> {

    private T body;
    @JsonSerialize(as = CallContext.class)
    @JsonDeserialize(as = CallContextImpl.class)
    private CallContext callContext;
    private Map<String, Object> runtimeContext;

    public Object getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public GenericResponse<T> body(T body) {
        this.body = body;
        return this;
    }

    public CallContext getCallContext() {
        return callContext;
    }

    public void setCallContext(CallContext callContext) {
        this.callContext = callContext;
    }

    public GenericResponse<T> callContext(CallContext callContext) {
        this.callContext = callContext;
        return this;
    }

    public Map<String, Object> getRuntimeContext() {
        return runtimeContext;
    }

    public void setRuntimeContext(Map<String, Object> runtimeContext) {
        this.runtimeContext = runtimeContext;
    }

    public GenericResponse<T> runtimeContext(Map<String, Object> runtimeContext) {
        this.runtimeContext = runtimeContext;
        return this;
    }

    @Override
    public String toString() {
        return "GenericResponse [body=" + body + ", callContext=" + callContext + ", runtimeContext=" + runtimeContext
                + "]";
    }

}
