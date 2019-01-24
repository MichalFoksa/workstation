package net.michalfoksa.workshop.station.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.michalfoksa.workshop.station.domain.WorkOrder;

@Controller
@RequestMapping(path="/works")
public class WorkController {

    private final Logger log = LoggerFactory
            .getLogger(WorkController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody String newWork (@RequestBody WorkOrder request) {
        log.debug("Request [request={}]", request);

        return "Done";
    }

}
