package com.colvir.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class RouterConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("search_route", p ->
                        p.path("/search")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("https://ya.ru/search")
                )
                .build();
    }
}
