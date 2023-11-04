package com.atomic.demo.interfaces.rest;

import com.atomic.demo.application.dto.payment.PaymentRequestDto;
import com.atomic.demo.application.dto.payment.PaymentResponseDto;
import com.atomic.demo.domain.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "Payment API", description = "Payment services")
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "결제 요청", description = "카드 정보로 결제를 요청 합니다.")
    @PostMapping("/pay")
    public Mono<ResponseEntity<PaymentResponseDto>> createPayment(
        @Parameter(
            name = "payment Request",
            description = "결제 정보",
            required = true
        )
        @Valid @RequestBody PaymentRequestDto paymentRequest) {
        return paymentService.savePaymentData(paymentRequest)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
