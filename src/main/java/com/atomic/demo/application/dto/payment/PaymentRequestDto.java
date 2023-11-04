package com.atomic.demo.application.dto.payment;

import com.atomic.demo.application.helper.VatDeserializer;
import com.atomic.demo.application.validation.annotation.OptionalPattern;
import com.atomic.demo.application.validation.annotation.ValidExpiryDate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
public class PaymentRequestDto {

    @Valid
    @JsonProperty("카드정보")
    private final CardInfo cardInfo;

    @Pattern(regexp = "^([0-9]|1[0-2])$", message = "할부 개월수는 0 ~ 12 사이의 숫자이어야 합니다.")
    @JsonProperty("할부개월수")
    private final String installmentMonths;

    @Pattern(regexp = "^[1-9][0-9]{2,8}$", message = "결제 금액은 최소 100원 ~ 최대 10억 이하이어야 하며 맨앞에 0이 들어갈 수 없습니다.")
    @JsonProperty("결제금액")
    private final String paymentAmount;

    @JsonDeserialize(using = VatDeserializer.class)
    @OptionalPattern(regexp = "^$|^[1-9][0-9]{0,8}$", message = "부가가치세는 비어있거나, 맨 앞에 0이 들어갈 수 없는 1 ~ 10억 이하의 숫자이어야 합니다.")
    @JsonProperty("부가가치세")
    @Setter
    private Optional<String> vat;

    @Getter
    @Builder
    @AllArgsConstructor(onConstructor = @__({@JsonCreator}))
    public static class CardInfo {
        @NotBlank(message = "카드번호는 필수입니다.")
        @Pattern(regexp = "^[1-9][0-9]{9,15}$", message = "카드 번호는 10자리 ~ 16자리 숫자이어야 합니다.")
        @JsonProperty("카드번호")
        private final String cardNumber;

        @ValidExpiryDate
        @JsonProperty("유효기간")
        private final String expirationDate;

        @Pattern(regexp = "^[1-9][0-9]{2}$", message = "CVC는 3자리 숫자이어야 합니다.")
        @JsonProperty("cvc")
        private final String cvc;
    }
}
