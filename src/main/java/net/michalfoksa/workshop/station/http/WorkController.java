package net.michalfoksa.workshop.station.http;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.michalfoksa.workshop.station.domain.WorkOrder;
import net.michalfoksa.workshop.station.domain.Workstation;

@Controller
@RequestMapping(path="/works")
public class WorkController {

    private final Logger log = LoggerFactory.getLogger(WorkController.class);

    @Value("${spring.application.name}")
    private String appName;

    @Inject
    private UriResolver uriResolver;

    @Inject
    private WorkstationClient workstationClient;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<Workstation> newWork (@RequestBody WorkOrder request) {
        log.debug("Request [request={}]", request);

        List<Workstation> nextWSResponse = new ArrayList<>();
        // TODO rework to stream()..collect(Collectors.toList());
        request.getNextStations().forEach(station -> {
            log.info("Next station [stationUrl={}]" , station.getUrl());
            nextWSResponse.addAll(workstationClient.orderWork(uriResolver.getUri(station) ,
                    new WorkOrder().parameters(station.getParameters())));
        });

        nextWSResponse.add(
                new Workstation().name(appName).parameters(request.getParameters()));
        return nextWSResponse;
    }

    @Service
    public class UriResolver {

        /***
         * Create next workstation URI from workstation name, or from workstation URL string if provided.
         *
         * @param workstation
         * @return workstation URI
         */
        public URI getUri(Workstation workstation) {
            if ( StringUtils.isEmpty(workstation.getUrl()) ) {
                return URI.create("http://" + workstation.getName());
            }
            return URI.create(workstation.getUrl());
        }
    }

}
