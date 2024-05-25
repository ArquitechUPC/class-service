package org.Arquitech.Gymrat.classservice.Class.resource.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Integer clientId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String stripeTransactionId;
    private Integer classId;
    private String paymentStatus;
}