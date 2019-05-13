package net.michalfoksa.demos.workshop.workstation.http.rest;

import net.michalfoksa.demos.workshop.workstation.rest.model.MessageContext;

public class ModelConverter {

    /***
     * Converts
     * {@link net.michalfoksa.demos.workshop.workstation.context.MessageContext}
     * to {@link MessageContext}.
     *
     * @param messageContextIn
     * @return
     */
    public static MessageContext toMessageContext(
            net.michalfoksa.demos.workshop.workstation.context.MessageContext messageContextIn) {
        return new MessageContext().correlationId(messageContextIn.getCorrelationId());
    }
}
