package com.colvir.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * Перехватчик запросов для feign-клиентов, проксирующий заголовки запроса во время внутримодульного взаимодействия
 */
public class ProxyRequestHeaderInterceptor implements RequestInterceptor {

    private final Set<String> proxyHeaders;

    public ProxyRequestHeaderInterceptor(String... proxyHeaders) {
        this.proxyHeaders = Set.of(proxyHeaders);
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        proxyHeaders.forEach(headerName -> proxyHeader(headerName, requestTemplate));
    }

    private void proxyHeader(String headerName, RequestTemplate requestTemplate) {
        getHeaderValue(headerName).ifPresent(headerValue -> requestTemplate.header(headerName, headerValue));
    }

    private Optional<String[]> getHeaderValue(String name) {
        return getOriginalRequest().map(request -> Collections.list(request.getHeaders(name)).toArray(new String[]{}));
    }

    private Optional<HttpServletRequest> getOriginalRequest() {
        return Optional.ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()))
                .map(ServletRequestAttributes::getRequest);
    }
}

