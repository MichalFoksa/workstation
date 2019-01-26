package net.michalfoksa.workshop.workstation.context;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class KubernetesRuntimeContext implements RuntimeContext {

    private String podIp;
    private String podname;
    private String namespace;
    private String nodeName;

    @Override
    public String getIp() {
        return podIp;
    }

    public void setIp(String ip) {
        this.podIp = ip;
    }

    @Override
    public String getHostname() {
        return podname;
    }

    public void setHostname(String hostname) {
        this.podname = hostname;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }


    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public Map<String, Object> getAllFieldsMap() {
        return Arrays.stream(KubernetesRuntimeContext.class.getDeclaredFields())
                .collect(Collectors.toMap(field -> field.getName(), field -> getFieldValue(field)));
    }

    private Object getFieldValue(Field field) {
        field.setAccessible(true);
        try {
            return field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // Ignore exceptions
        }
        return new Object();
    }

    @Override
    public String toString() {
        return "KubernetesRuntimeContext [podIp=" + podIp + ", podname=" + podname + ", namespace=" + namespace
                + ", nodeName=" + nodeName + "]";
    }

}
