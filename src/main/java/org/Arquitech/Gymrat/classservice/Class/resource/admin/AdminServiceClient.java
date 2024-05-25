package org.Arquitech.Gymrat.classservice.Class.resource.admin;

import org.Arquitech.Gymrat.classservice.Class.resource.payment.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "admin-service", url = "http://localhost:8082")
public interface AdminServiceClient {

    @PostMapping("/api/v1/admin/payments")
    void notifyPayment(@RequestBody PaymentDto paymentDto);
}