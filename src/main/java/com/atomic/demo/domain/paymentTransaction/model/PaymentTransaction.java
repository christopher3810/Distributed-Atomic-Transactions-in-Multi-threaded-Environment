package com.atomic.demo.domain.paymentTransaction.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.relational.core.mapping.Table;

@Table("transactions")
@Getter
@Setter
@NoArgsConstructor
public abstract class PaymentTransaction {

    @Id
    private String uniqueId;  // 관리 번호, 20자리

    private String paymentAmount;
    private String vat;
    private DataType dataType; // 결제 or 취소

    private String encryptedPaymentData;
}
