package org.Arquitech.Gymrat.classservice.Class.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.Optional;

@FeignClient(name = "client-service", url = "http://localhost:8080", path = "/api/v1/clients")
public interface ClientClass {
    @GetMapping("{id}")
    Optional<?> fetchById(@PathVariable Integer id);

    @GetMapping
    Optional<?> getGoals(Optional<?> Client);
}
