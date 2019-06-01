package net.michalfoksa.demo.workshop.workstation.http.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import net.michalfoksa.demo.workshop.workstation.context.RuntimeContext;
import net.michalfoksa.demo.workshop.workstation.rest.api.WorkOrdersApi;
import net.michalfoksa.demo.workshop.workstation.rest.model.CreateWorkOrderResponse;
import net.michalfoksa.demo.workshop.workstation.rest.model.WorkOrder;
import net.michalfoksa.demo.workshop.workstation.rest.model.Workstation;
import net.michalfoksa.demo.workshop.workstation.service.UriResolver;
import net.michalfoksa.demo.workshop.workstation.service.WorkstationClientService;

@RestController
public class WorkOrdersController implements WorkOrdersApi {

    Logger log = LoggerFactory.getLogger(WorkOrdersController.class);

    @Inject
    private RuntimeContext runtimeContext;

    @Inject
    private UriResolver uriResolver;

    @Inject
    private WorkstationClientService workstationService;

    @Override
    public ResponseEntity<List<CreateWorkOrderResponse>> createWorkOrder(@Valid WorkOrder workOrder) {
        log.debug("Request [workOrder={}]", workOrder);

        List<CreateWorkOrderResponse> response = new ArrayList<>();
        // Add response of current workstation at beginning of the all responses
        // array.
        response.add(new CreateWorkOrderResponse().body(new Workstation()
                .name(workOrder.getWorkstationName() + " application: " + runtimeContext.getApplication())
                .parameters(workOrder.getParameters())));

        if (workOrder.getNextStations().size() > 0) {
            Workstation nextStation = workOrder.getNextStations().get(0);
            log.info("Next station [name={}]", nextStation.getName());

            // Create list of workstations following after next one.
            // Remove next workstation form list all following workstations
            List<Workstation> nextStations = workOrder.getNextStations().stream()
                    .filter(station -> !nextStation.equals(station)).collect(Collectors.toList());

            // Call next workstation
            response.addAll(workstationService.createWorkOrder(uriResolver.getUri(nextStation),
                    new WorkOrder().workstationName(nextStation.getName()).parameters(nextStation.getParameters())
                    .nextStations(nextStations)));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
