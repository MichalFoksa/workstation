package net.michalfoksa.demo.workshop.workstation.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.michalfoksa.demo.workshop.workstation.rest.model.Workstation;

/***
 * Service discovery using Kubernetes environment variables.
 *
 * @author Michal Foksa
 *
 */
@Service
public class UriResolver implements InitializingBean {
    Logger log = LoggerFactory.getLogger(UriResolver.class);

    @Value("${service.discovery.client.default-prototol:http}")
    private String defaultProtocol;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("[defaultProtocol={}]", defaultProtocol);
    }

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
             * [SERVICE_NAME]_SERVICE_HOST
             * [SERVICE_NAME]_SERVICE_PORT_[PORT_NAME]
             */

            // Replace each dash with underscore
            String normalizeServiceName = workstation.getName().replaceAll("-", "_").toUpperCase();

            String host = System.getenv(normalizeServiceName + "_SERVICE_HOST");
            String port = System
                    .getenv(normalizeServiceName + "_SERVICE_PORT_" + defaultProtocol.toUpperCase());
            return URI.create(defaultProtocol + "://" + host + ":" + port);
        }
        return URI.create(workstation.getUrl());
    }
}