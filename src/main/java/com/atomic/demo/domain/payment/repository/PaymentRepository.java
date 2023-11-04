package com.atomic.demo.domain.payment.repository;

import com.atomic.demo.domain.payment.model.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {
    Mono<Payment> findByUniqueId(String uniqueId);
}

