package net.michalfoksa.demo.workshop.workstation.config;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextListener;

import net.michalfoksa.demo.workshop.workstation.context.KubernetesRuntimeContext;
import net.michalfoksa.demo.workshop.workstation.context.MessageContext;
import net.michalfoksa.demo.workshop.workstation.context.MessageContextImpl;
import net.michalfoksa.demo.workshop.workstation.context.RuntimeContext;

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
        MessageContextImpl messageContext = new MessageContextImpl()
                .correlationId(request.getHeader("x-correlation-id") != null ? request.getHeader("x-correlation-id")
                        : UUID.randomUUID().toString())
                .returnContexts(true);

        String returnContexts = request.getParameter("returnContexts");
        if (returnContexts != null && "false".equals(returnContexts.toLowerCase())) {
            messageContext.setReturnContexts(false);
        }

        log.debug("Message context [messageContext={}]", messageContext);
        return messageContext;
    }

    @Value("${spring.application.name}")
    private String application;

    @Value("${spring.cloud.client.hostname}")
    private String hostname;

    @Value("${spring.cloud.client.ip-address}")
    private String ipAddress;

    @Value("${POD_NAME:#{null}}")
    private String podName;

    @Value("${POD_IP:#{null}}")
    private String podIp;

    @Value("${POD_NAMESPACE:#{null}}")
    private String podNamespace;

    @Value("${NODE_NAME:#{null}}")
    private String nodeName;

    @Bean
    public RuntimeContext runtimeContext() {
        KubernetesRuntimeContext rt = new KubernetesRuntimeContext().application(application)
                .hostname(podName != null ? podName : hostname).ip(podIp != null ? podIp : ipAddress)
                .podNamespace(podNamespace).nodeName(nodeName);

        log.debug("Runtime context [runtimeContext={}]", rt);

        return rt;
    }

}
