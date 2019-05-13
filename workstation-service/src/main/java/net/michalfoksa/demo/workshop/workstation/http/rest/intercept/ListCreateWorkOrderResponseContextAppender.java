package net.michalfoksa.demo.workshop.workstation.http.rest.intercept;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import net.michalfoksa.demo.workshop.workstation.context.MessageContext;
import net.michalfoksa.demo.workshop.workstation.context.RuntimeContext;
import net.michalfoksa.demo.workshop.workstation.http.rest.ModelConverter;
import net.michalfoksa.demo.workshop.workstation.rest.model.CreateWorkOrderResponse;

/***
 * Applicable for List<GenericResponse<?>> response types.
 *
 * Set message and runtime contexts into the first (with index 0)
 * GenericResponse element.
 *
 * @author Michal Foksa
 *
 */
@RestControllerAdvice(basePackages = "net.michalfoksa.demo.workshop.workstation.http.rest")
public class ListCreateWorkOrderResponseContextAppender implements ResponseBodyAdvice<List<CreateWorkOrderResponse>> {

    private final Logger log = LoggerFactory.getLogger(ListCreateWorkOrderResponseContextAppender.class);

    @Inject
    private MessageContext messageContext;

    @Inject
    private RuntimeContext runtimeContext;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        // Is it desired to render context and is return type subclass of
        // java.util.ResponseEntity and JSON message converter
        if (messageContext.isReturnContexts() && ResponseEntity.class.isAssignableFrom(returnType.getParameterType())
                && converterType.isAssignableFrom(MappingJackson2HttpMessageConverter.class)) {
            log.debug("[supports] [returnType={}, converterType={}]", returnType, converterType);
            return true;
        }
        return false;
    }

    @Override
    public List<CreateWorkOrderResponse> beforeBodyWrite(List<CreateWorkOrderResponse> body, MethodParameter returnType,
            MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {

        log.debug(
                "[beforeBodyWrite] [bodyClass={}, body={}, returnType={}, selectedContentType={}, selectedConverterType={}, request={}, response={}]",
                body != null ? body.getClass() : "null", body, returnType, selectedContentType, selectedConverterType,
                        request, response);

        /**
         * If body is List<CreateWorkOrderResponse> set message and runtime
         * contexts into the first (with index 0) element.
         */
        if (body != null) {
            if (body.get(0) instanceof CreateWorkOrderResponse) {
                body.get(0).messageContext(ModelConverter.toMessageContext(messageContext))
                .runtimeContext(runtimeContext.getAllFieldsMap());

                log.info("[beforeBodyWrite] Contexts appended.");
            } else {
                log.debug("[beforeBodyWrite] First list element is not type of CreateWorkOrderResponse [class={}]",
                        body.get(0) != null ? body.get(0).getClass() : "null");
            }
        }

        return body;
    }

}
