package net.michalfoksa.demos.workshop.workstation.http.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.michalfoksa.demos.workshop.workstation.context.RuntimeContext;
import net.michalfoksa.demos.workshop.workstation.rest.api.WorkOrdersApi;
import net.michalfoksa.demos.workshop.workstation.rest.model.CreateWorkorderResponse;
import net.michalfoksa.demos.workshop.workstation.rest.model.WorkOrder;
import net.michalfoksa.demos.workshop.workstation.rest.model.Workstation;
import net.michalfoksa.demos.workshop.workstation.service.WorkstationClientService;

@RestController
public class WorkOrdersController implements WorkOrdersApi {

    Logger log = LoggerFactory.getLogger(WorkController.class);

    @Inject
    private RuntimeContext runtimeContext;

    @Inject
    private UriResolver uriResolver;

    @Inject
    private WorkstationClientService workstationService;

    @RequestMapping(value = "/workorders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<CreateWorkorderResponse>> createWorkOrder(@Valid WorkOrder workOrder) {
        log.debug("Request [workOrder={}]", workOrder);

        List<CreateWorkorderResponse> response = new ArrayList<>();
        // Add response of current workstation at beginning of the all responses
        // array.
        response.add(new CreateWorkorderResponse().body(new Workstation()
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

    /***
     * Service discovery using Kubernetes environment variables.
     *
     * @author Michal Foksa
     *
     */
    @Service
    public class UriResolver {

        @Value("${service.discovery.client.defaultprototol:http}")
        private String defaultProtocol;

        /***
         * Create next workstation URI from workstation name, or from
         * workstation URL string if provided.
         *
         * @param workstation
         * @return workstation URI
         */
        public URI getUri(Workstation workstation) {
            if (StringUtils.isEmpty(workstation.getUrl())) {

                /**
                 * Service discovery using Kubernetes environment variables.
                 * Host and port variables format is:
                 *
                 * [SERVESE_NAME]_SERVICE_HOST
                 * [SERVESE_NAME]_SERVICE_PORT_[PORT_NAME]
                 */
                String host = System.getenv(workstation.getName().toUpperCase() + "_SERVICE_HOST");
                String port = System
                        .getenv(workstation.getName().toUpperCase() + "_SERVICE_PORT_" + defaultProtocol.toUpperCase());
                return URI.create(defaultProtocol + "://" + host + ":" + port);
            }
            return URI.create(workstation.getUrl());
        }
    }

}