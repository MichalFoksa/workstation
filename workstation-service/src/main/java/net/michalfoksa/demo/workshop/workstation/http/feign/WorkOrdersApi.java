package net.michalfoksa.demo.workshop.workstation.http.feign;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import net.michalfoksa.demo.workshop.workstation.rest.model.CreateWorkOrderResponse;
import net.michalfoksa.demo.workshop.workstation.rest.model.WorkOrder;

@FeignClient(name = "WorkOrdersApi", url = "https://this-is-a-placeholder.com")
public interface WorkOrdersApi {

    @PostMapping(value = "/workorders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CreateWorkOrderResponse>> createWorkOrder(URI workstationUri,
            @RequestHeader HttpHeaders headers, @RequestParam boolean returnContexts, @RequestBody WorkOrder workOrder);

}

