package net.michalfoksa.workshop.workstation.api;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import net.michalfoksa.workshop.workstation.context.MessageContext;
import net.michalfoksa.workshop.workstation.domain.GenericResponse;
import net.michalfoksa.workshop.workstation.domain.WorkOrder;
import net.michalfoksa.workshop.workstation.domain.Workstation;
import net.michalfoksa.workshop.workstation.http.feign.WorkstationClient;

@Service
public class WorkstationApi {

    @Inject
    private MessageContext messageContext;

    @Inject
    private WorkstationClient workstationClient;

    public List<GenericResponse<Workstation>> orderWork(URI workstationUri, WorkOrder workOrder) {
        // Pass correlation ID to downstream service.
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-correlation-id", messageContext.getCorrelationId());

        return workstationClient.orderWork(workstationUri, headers, workOrder);
    }

}
