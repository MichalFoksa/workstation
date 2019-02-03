package net.michalfoksa.demos.workshop.workstation.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import net.michalfoksa.demos.workshop.workstation.context.MessageContext;
import net.michalfoksa.demos.workshop.workstation.domain.GenericResponse;
import net.michalfoksa.demos.workshop.workstation.domain.WorkOrder;
import net.michalfoksa.demos.workshop.workstation.domain.Workstation;
import net.michalfoksa.demos.workshop.workstation.http.feign.WorkstationClient;

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

        try {
            return workstationClient.orderWork(workstationUri, headers, messageContext.isReturnContexts(), workOrder);
        } catch (Exception e) {
            List<GenericResponse<Workstation>> response = new ArrayList<>();
            response.add(new GenericResponse<Workstation>()
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
