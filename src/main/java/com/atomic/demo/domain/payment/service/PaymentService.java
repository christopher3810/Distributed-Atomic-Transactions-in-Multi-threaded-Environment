package com.atomic.demo.domain.payment.service;

import com.atomic.demo.domain.payment.model.dto.PaymentRequestDto;
import com.atomic.demo.domain.payment.model.dto.PaymentResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    public Mono<PaymentResponseDto> savePaymentData(PaymentRequestDto requestDto) {
        return Mono.just(new PaymentResponseDto("",""));
    }

}
