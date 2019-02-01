package net.michalfoksa.demos.workshop.workstation.config;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextListener;

import net.michalfoksa.demos.workshop.workstation.context.KubernetesRuntimeContext;
import net.michalfoksa.demos.workshop.workstation.context.MessageContext;
import net.michalfoksa.demos.workshop.workstation.context.MessageContextImpl;
import net.michalfoksa.demos.workshop.workstation.context.RuntimeContext;

@Configuration
public class ContextConfig {

    private final Logger log = LoggerFactory.getLogger(ContextConfig.class);

    // Exposes current request to the current thread
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

    @Bean
    @RequestScope
    public MessageContext messageContext(HttpServletRequest request) {
        MessageContext messageContext = new MessageContextImpl()
                .correlationId(request.getHeader("x-correlation-id") != null ? request.getHeader("x-correlation-id")
                        : UUID.randomUUID().toString());
        log.debug("Message context [messageContext={}]", messageContext);
        return messageContext;
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.cloud.client.hostname}")
    private String hostname;

    @Value("${spring.cloud.client.ip-address}")
    private String ipAddress;

    @Value("${POD_NAME:#{null}}")
    private String podName;

    @Value("${POD_IP:#{null}}")
    private String podIp;

    @Value("${NODE_NAME:#{null}}")
    private String nodeName;

    @Value("${POD_NAMESPACE:#{null}}")
    private String namespace;

    @Bean
    public RuntimeContext runtimeContext() {
        KubernetesRuntimeContext rt = new KubernetesRuntimeContext();
        rt.setApplicationName(applicationName);
        rt.setHostname(podName != null ? podName : hostname);
        rt.setIp(podIp != null ? podIp : ipAddress);
        rt.setNodeName(nodeName);
        rt.setNamespace(namespace);

        log.debug("Runtime context [runtimeContext={}]", rt);

        return rt;
    }


}
