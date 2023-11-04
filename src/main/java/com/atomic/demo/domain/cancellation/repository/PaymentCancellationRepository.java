package com.atomic.demo.domain.cancellation.repository;

import com.atomic.demo.domain.cancellation.model.PaymentCancellation;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PaymentCancellationRepository extends ReactiveCrudRepository<PaymentCancellation, String> {
    @Query("SELECT * FROM cancellations pc WHERE pc.original_payment_id = :paymentId")
    Mono<PaymentCancellation> findByOriginalPaymentId(String paymentId);
}
