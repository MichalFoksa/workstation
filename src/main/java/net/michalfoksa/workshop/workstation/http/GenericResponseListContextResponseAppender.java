package net.michalfoksa.workshop.workstation.http;

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

import net.michalfoksa.workshop.workstation.context.CallContext;
import net.michalfoksa.workshop.workstation.context.RuntimeContext;
import net.michalfoksa.workshop.workstation.domain.GenericResponse;

@RestControllerAdvice
public class GenericResponseListContextResponseAppender implements ResponseBodyAdvice<Object> {

    private final Logger log = LoggerFactory.getLogger(GenericResponseListContextResponseAppender.class);

    @Inject
    private CallContext callContext;

    @Inject
    private RuntimeContext runtimeContext;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.debug("[supports] [returnType={}, converterType={}]", returnType, converterType);

        return converterType.isAssignableFrom(MappingJackson2HttpMessageConverter.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                    ServerHttpResponse response) {
        log.debug(
                "[beforeBodyWrite] [bodyClass={}, body={}, returnType={}, selectedContentType={}, selectedConverterType={}, request={}, response={}]",
                body.getClass(), body, returnType, selectedContentType, selectedConverterType, request, response);

        if (body instanceof List<?>) {
            List<?> l = (List<?>) body;
            if (l.get(0) instanceof GenericResponse<?>) {
                ((GenericResponse<?>) l.get(0)).callContext(callContext)
                .runtimeContext(runtimeContext.getAllFieldsMap());
            }
        }

        return body;
    }

}
