package com.atomic.demo.domain.payment.rest;

import com.atomic.demo.domain.payment.model.dto.PaymentRequestDto;
import com.atomic.demo.domain.payment.model.dto.PaymentResponseDto;
import com.atomic.demo.domain.payment.service.PaymentService;
import com.atomic.demo.domain.redisLock.service.RedisLockService;
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
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Tag(name = "Payment API", description = "Payment services")
public class PaymentController {

    private final PaymentService paymentService;
    private final RedisLockService lockService;

    @Operation(summary = "결제 요청", description = "카드 정보로 결제를 요청 합니다.")
    @PostMapping("/pay")
    public Mono<ResponseEntity<PaymentResponseDto>> createPayment(
        @Parameter(
            name = "payment Request",
            description = "결제 정보",
            required = true
        )
        @Valid @RequestBody PaymentRequestDto paymentRequest) {
        String cardNumber = paymentRequest.getCardInfo().getCardNumber();
        return lockService.acquireLock(cardNumber)
            .flatMap(locked -> paymentService.savePaymentData(paymentRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
            .publishOn(Schedulers.boundedElastic())
            .doFinally(signalType -> lockService.releaseLock(cardNumber).subscribe());
    }

}
