package com.colvir.feign;

import com.colvir.feign.dto.ClientDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@LoadBalancerClient(name = "ClientService")
@FeignClient(name = "ClientService", fallback = ClientServiceFallback.class)
public interface ClientServiceClient {

    @GetMapping("/get/{id}")
    Optional<ClientDto> findById(@PathVariable Long id);

}
