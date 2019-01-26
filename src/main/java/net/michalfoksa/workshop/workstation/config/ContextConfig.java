package net.michalfoksa.workshop.workstation.config;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextListener;

import net.michalfoksa.workshop.workstation.context.CallContext;
import net.michalfoksa.workshop.workstation.context.CallContextImpl;
import net.michalfoksa.workshop.workstation.context.KubernetesRuntimeContext;
import net.michalfoksa.workshop.workstation.context.RuntimeContext;

@Configuration
public class ContextConfig {

    // Exposes current request to the current thread
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

    @Bean
    @RequestScope
    public CallContext callContext(HttpServletRequest request) {
        return new CallContextImpl()
                .correlationId(request.getHeader("x-correlation-id") != null ? request.getHeader("x-correlation-id")
                        : UUID.randomUUID().toString());
    }

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
        rt.setHostname(podName != null ? podName : hostname);
        rt.setIp(podIp != null ? podIp : ipAddress);
        rt.setNodeName(nodeName);
        rt.setNamespace(namespace);
        return rt;
    }


}
