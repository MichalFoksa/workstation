package net.michalfoksa.workshop.station.http;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import net.michalfoksa.workshop.station.domain.WorkOrder;
import net.michalfoksa.workshop.station.domain.Workstation;

@FeignClient(name = "workstationClient", url = "https://this-is-a-placeholder.com")
public interface WorkstationClient {

    @PostMapping(path = "/works")
    List<Workstation> orderWork(URI workstationUrl, @RequestBody WorkOrder workOrder);
}

