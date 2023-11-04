package com.atomic.demo.domain.payment.model;

import com.atomic.demo.domain.paymentTransaction.model.PaymentTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("payments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Payment extends PaymentTransaction {

    private int installmentMonths;
}

