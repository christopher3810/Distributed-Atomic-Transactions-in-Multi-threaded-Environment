package com.atomic.demo.domain.payment.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentResponseDto(

    @JsonProperty("관리 번호")
    String uniqId,
    // 카드사에 전달한 String Data (공통 데이터 + 데이터 부문)
    @JsonProperty("카드사에 전달한 String 데이터")
    String encryptedPaymentData) {
}