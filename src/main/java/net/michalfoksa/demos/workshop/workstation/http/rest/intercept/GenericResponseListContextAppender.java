package net.michalfoksa.demos.workshop.workstation.http.rest.intercept;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import net.michalfoksa.demos.workshop.workstation.context.MessageContext;
import net.michalfoksa.demos.workshop.workstation.context.RuntimeContext;
import net.michalfoksa.demos.workshop.workstation.domain.GenericResponse;

/***
 * Applicable for List<GenericResponse<?>> response types.
 *
 * Set message and runtime contexts into the first (with index 0)
 * GenericResponse element.
 *
 * @author Michal Foksa
 *
 */
@RestControllerAdvice(basePackages = "net.michalfoksa.demos.workshop.workstation.http.rest")
public class GenericResponseListContextAppender implements ResponseBodyAdvice<Object> {

    private final Logger log = LoggerFactory.getLogger(GenericResponseListContextAppender.class);

    @Inject
    private MessageContext messageContext;

    @Inject
    private RuntimeContext runtimeContext;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        // Is it desired to render context and is return type subclass of
        // java.util.List and JSON message converter
        if (messageContext.isReturnContexts() && List.class.isAssignableFrom(returnType.getParameterType())
                && converterType.isAssignableFrom(MappingJackson2HttpMessageConverter.class)) {
            log.debug("[supports] [returnType={}, converterType={}]", returnType, converterType);
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                    ServerHttpResponse response) {
        log.debug(
                "[beforeBodyWrite] [bodyClass={}, body={}, returnType={}, selectedContentType={}, selectedConverterType={}, request={}, response={}]",
                body != null ? body.getClass() : "null", body, returnType, selectedContentType, selectedConverterType,
                        request, response);

        /**
         * If body is List<GenericResponse<?>> set message and runtime contexts
         * into the first (with index 0) element.
         */
        // Mainly to check if body != null
        if (body instanceof List<?>) {
            List<?> list = (List<?>) body;
            if (list.get(0) instanceof GenericResponse<?>) {
                ((GenericResponse<?>) list.get(0)).messageContext(messageContext)
                .runtimeContext(runtimeContext.getAllFieldsMap());

                log.info("[beforeBodyWrite] Contexts appended.");
            } else {
                log.debug("[beforeBodyWrite] First list element is not type of GenericResponse<?> [class={}]",
                        list.get(0) != null ? list.get(0).getClass() : "null");
            }
        }

        return body;
    }

}
