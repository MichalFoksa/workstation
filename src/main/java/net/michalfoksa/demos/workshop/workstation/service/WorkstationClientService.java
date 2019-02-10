package net.michalfoksa.demos.workshop.workstation.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import net.michalfoksa.demos.workshop.workstation.context.MessageContext;
import net.michalfoksa.demos.workshop.workstation.http.feign.WorkOrdersApi;
import net.michalfoksa.demos.workshop.workstation.rest.model.CreateWorkorderResponse;
import net.michalfoksa.demos.workshop.workstation.rest.model.WorkOrder;
import net.michalfoksa.demos.workshop.workstation.rest.model.Workstation;

@Service
public class WorkstationClientService {

    @Inject
    private MessageContext messageContext;

    @Inject
    private WorkOrdersApi workOrdersApi;

    public List<CreateWorkorderResponse> createWorkOrder(URI workstationUri, WorkOrder workOrder) {
        // Pass correlation ID to downstream service.
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-correlation-id", messageContext.getCorrelationId());

        try {
            return workOrdersApi.createWorkOrder(workstationUri, headers, messageContext.isReturnContexts(), workOrder)
                    .getBody();
        } catch (Exception e) {
            List<CreateWorkorderResponse> response = new ArrayList<>();
            response.add(new CreateWorkorderResponse()
                    .body(new Workstation().name("Error occured connecting to " + workOrder.getWorkstationName())
                            .parameters(collectThrowables(e))));
            return response;
        }
    }

    private HashMap<String, String> collectThrowables(Throwable throwable) {
        // Collect all exceptions into parameters.
        HashMap<String, String> ret = new HashMap<>();
        int i = 1;
        for (Throwable cause = throwable; cause != null; cause = cause.getCause(), i++) {
            ret.put("message" + i, cause.getMessage());
            ret.put("exception" + i, cause.getClass().getName());
        }
        return ret;
    }

}
