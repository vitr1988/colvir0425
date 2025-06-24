package com.colvir.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadBalancerConfiguration {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    @Primary
//    ServiceInstanceListSupplier serviceInstanceListSupplier() {
//        return new StaticInstanceListSuppler("ClientService");
//    }
//
//    @Getter
//    @RequiredArgsConstructor
//    static class StaticInstanceListSuppler implements ServiceInstanceListSupplier {
//
//        private final String serviceId;
//
//        @Override
//        public Flux<List<ServiceInstance>> get() {
//            return Flux.just(List.of(
//                    new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8801, false),
//                    new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8805, false))
//            );
//        }
//    }
}
