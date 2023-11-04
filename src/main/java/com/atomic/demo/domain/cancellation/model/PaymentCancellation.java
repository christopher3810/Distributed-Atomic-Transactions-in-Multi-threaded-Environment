package com.atomic.demo.domain.cancellation.model;

import com.atomic.demo.domain.paymentTransaction.model.PaymentTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("cancellations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentCancellation extends PaymentTransaction {

    @Column("original_payment_id")
    private String originalPaymentId; // 원래의 결제 데이터의 ID

    @Column("installment_months")
    private String installmentMonths = "00";
}

