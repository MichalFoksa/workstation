package net.michalfoksa.demos.workshop.workstation.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.michalfoksa.demos.workshop.workstation.context.MessageContext;
import net.michalfoksa.demos.workshop.workstation.context.MessageContextImpl;

/***
 * Generic REST response with payload as body and message and runtime contexts.
 *
 * @author Michal Foksa
 *
 * @param <T>
 */
@JsonInclude(Include.NON_NULL)
public class GenericResponse<T> {

    private T body;
    @JsonSerialize(as = MessageContext.class)
    @JsonDeserialize(as = MessageContextImpl.class)
    private MessageContext messageContext;
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

    public MessageContext getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(MessageContext messageContext) {
        this.messageContext = messageContext;
    }

    public GenericResponse<T> messageContext(MessageContext messageContext) {
        this.messageContext = messageContext;
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
        return "GenericResponse [body=" + body + ", messageContext=" + messageContext + ", runtimeContext="
                + runtimeContext + "]";
    }

}
