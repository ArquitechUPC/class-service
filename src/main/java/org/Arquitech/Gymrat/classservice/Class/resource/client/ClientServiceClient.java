package org.Arquitech.Gymrat.classservice.Class.resource.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "client-service", url = "http://localhost:8081") // Ajusta la URL
public interface ClientServiceClient {

    @GetMapping("/api/v1/clients/{clientId}")
    ClientDto getClientById(@PathVariable Integer clientId);
}