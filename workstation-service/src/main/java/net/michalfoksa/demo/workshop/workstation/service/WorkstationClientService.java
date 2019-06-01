package net.michalfoksa.demo.workshop.workstation.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import net.michalfoksa.demo.workshop.workstation.context.MessageContext;
import net.michalfoksa.demo.workshop.workstation.http.feign.WorkOrdersApi;
import net.michalfoksa.demo.workshop.workstation.rest.model.CreateWorkOrderResponse;
import net.michalfoksa.demo.workshop.workstation.rest.model.WorkOrder;
import net.michalfoksa.demo.workshop.workstation.rest.model.Workstation;

@Service
public class WorkstationClientService {

    @Inject
    private MessageContext messageContext;

    @Inject
    private WorkOrdersApi workOrdersApi;

    public List<CreateWorkOrderResponse> createWorkOrder(URI workstationUri, WorkOrder workOrder) {
        // Pass correlation ID to downstream service.
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-correlation-id", messageContext.getCorrelationId());

        try {
            return workOrdersApi.createWorkOrder(workstationUri, headers,
                    messageContext.isReturnContexts() ? "true" : null, workOrder).getBody();
        } catch (Exception e) {
            List<CreateWorkOrderResponse> response = new ArrayList<>();
            response.add(new CreateWorkOrderResponse()
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
