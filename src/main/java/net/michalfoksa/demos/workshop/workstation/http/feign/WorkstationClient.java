package net.michalfoksa.demos.workshop.workstation.http.feign;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import net.michalfoksa.demos.workshop.workstation.domain.GenericResponse;
import net.michalfoksa.demos.workshop.workstation.domain.WorkOrder;
import net.michalfoksa.demos.workshop.workstation.domain.Workstation;

@FeignClient(name = "workstationClient", url = "https://this-is-a-placeholder.com")
public interface WorkstationClient {

    @PostMapping(path = "/works")
    List<GenericResponse<Workstation>> orderWork(URI workstationUri, @RequestHeader HttpHeaders headers,
            @RequestBody WorkOrder workOrder);

}

