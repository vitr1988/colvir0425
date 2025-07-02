package com.colvir.webinar26.config;

import com.colvir.webinar26.dto.DepartmentDto;
import com.colvir.webinar26.mapper.DepartmentMapper;
import com.colvir.webinar26.service.DepartmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        return route()
                .GET("/api/departments", accept(APPLICATION_JSON),
                        request -> ok().contentType(APPLICATION_JSON)
                                .body(departmentService.findAll().map(departmentMapper::toDto), DepartmentDto.class)
                )
                .DELETE("/api/departments/{id}",
                        request -> departmentService.deleteById(request.pathVariable("id")).then(ok().build())
                ).build();
    }
}
