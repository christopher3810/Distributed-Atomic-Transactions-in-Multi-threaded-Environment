package com.atomic.demo.interfaces.rest;

import com.atomic.demo.application.dto.payment.PaymentRequestDto;
import com.atomic.demo.application.dto.payment.PaymentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "Payment API", description = "Payment services")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Operation(summary = "Create a new payment", description = "Creates a new payment transaction")
    @PostMapping
    public Mono<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto paymentRequest) {

    }

}
