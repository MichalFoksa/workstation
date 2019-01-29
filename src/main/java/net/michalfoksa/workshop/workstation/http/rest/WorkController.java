package net.michalfoksa.workshop.workstation.http.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.michalfoksa.workshop.workstation.api.WorkstationApi;
import net.michalfoksa.workshop.workstation.domain.GenericResponse;
import net.michalfoksa.workshop.workstation.domain.WorkOrder;
import net.michalfoksa.workshop.workstation.domain.Workstation;

@RestController
@RequestMapping(path = "/works")
public class WorkController {

    private final Logger log = LoggerFactory.getLogger(WorkController.class);

    @Value("${spring.application.name}")
    private String appName;

    @Inject
    private UriResolver uriResolver;

    @Inject
    private WorkstationApi workstationApi;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<GenericResponse<Workstation>> createWorkOrder(@RequestBody WorkOrder request) {
        log.debug("Request [request={}]", request);

        List<GenericResponse<Workstation>> response = new ArrayList<>();
        // Add response of current workstation at beginning of the all responses
        // array.
        response.add(new GenericResponse<Workstation>().body(new Workstation()
                .name(request.getWorkstationName() + " appName: " + appName).parameters(request.getParameters())));

        if (request.getNextStations().size() > 0) {
            Workstation nextStation = request.getNextStations().get(0);
            log.info("Next station [name={}]", nextStation.getName());

            // Create list of workstations following after next one.
            // Remove next workstation form list all following workstations
            List<Workstation> nextStations = request.getNextStations().stream()
                    .filter(station -> !nextStation.equals(station))
                    .collect(Collectors.toList());

            // Call next workstation
            response.addAll(workstationApi.orderWork(uriResolver.getUri(nextStation),
                    new WorkOrder()
                    .workstationName(nextStation.getName())
                    .parameters(nextStation.getParameters())
                    .nextStations(nextStations)));
        }

        return response;
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
                String host = System.getenv(workstation.getName() + "_SERVICE_HOST");
                String port = System
                        .getenv(workstation.getName().toUpperCase() + "_SERVICE_PORT_" + defaultProtocol.toUpperCase());
                return URI.create(defaultProtocol + "://" + host + ":" + port);
            }
            return URI.create(workstation.getUrl());
        }
    }

}
