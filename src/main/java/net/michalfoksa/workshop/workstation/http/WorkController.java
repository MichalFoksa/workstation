package net.michalfoksa.workshop.workstation.http;

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

import net.michalfoksa.workshop.workstation.domain.GenericResponse;
import net.michalfoksa.workshop.workstation.domain.WorkOrder;
import net.michalfoksa.workshop.workstation.domain.Workstation;
import net.michalfoksa.workshop.workstation.http.feign.WorkstationClient;

@RestController
@RequestMapping(path = "/works")
public class WorkController {

    private final Logger log = LoggerFactory.getLogger(WorkController.class);

    @Value("${spring.application.name}")
    private String appName;

    @Inject
    private UriResolver uriResolver;

    @Inject
    private WorkstationClient workstationClient;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<GenericResponse<Workstation>> newWork(@RequestBody WorkOrder request) {
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
            response.addAll(workstationClient.orderWork(uriResolver.getUri(nextStation),
                    new WorkOrder()
                    .workstationName(nextStation.getName())
                    .parameters(nextStation.getParameters())
                    .nextStations(nextStations)));
        }

        return response;
    }

    @Service
    public class UriResolver {

        /***
         * Create next workstation URI from workstation name, or from
         * workstation URL string if provided.
         *
         * @param workstation
         * @return workstation URI
         */
        public URI getUri(Workstation workstation) {
            if (StringUtils.isEmpty(workstation.getUrl())) {
                return URI.create("http://" + workstation.getName());
            }
            return URI.create(workstation.getUrl());
        }
    }

}
