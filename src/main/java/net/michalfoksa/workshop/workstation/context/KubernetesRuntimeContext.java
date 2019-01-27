package net.michalfoksa.workshop.workstation.context;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class KubernetesRuntimeContext implements RuntimeContext {

    private String podIp;
    private String podName;
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
        return podName;
    }

    public void setHostname(String hostname) {
        this.podName = hostname;
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
                .filter(this::isNotNull)
                .collect(Collectors.toMap(field -> field.getName(), field -> getFieldValue(field)));
    }

    /***
     * Return true if field value is not null
     *
     * @param field
     * @return true if field value is not null
     */
    private boolean isNotNull(Field field) {
        return getFieldValue(field) != null;
    }

    private Object getFieldValue(Field field) {
        field.setAccessible(true);
        try {
            return field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // Ignore exceptions
        }
        return null;
    }

    @Override
    public String toString() {
        return "KubernetesRuntimeContext [podIp=" + podIp + ", podName=" + podName + ", namespace=" + namespace
                + ", nodeName=" + nodeName + "]";
    }

}
