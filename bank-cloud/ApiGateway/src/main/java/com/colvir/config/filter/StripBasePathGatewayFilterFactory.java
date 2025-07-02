package com.colvir.config.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * Фабрика для фильтров, позволяющая убирать basePath при проксировании запросов до конечных сервисов
 */
public class StripBasePathGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest req = exchange.getRequest();
            String path = req.getURI().getRawPath();

            ServerHttpRequest request = req.mutate().path(path).contextPath(null).build();

            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}

