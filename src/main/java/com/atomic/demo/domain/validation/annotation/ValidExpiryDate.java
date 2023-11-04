package com.atomic.demo.domain.validation.annotation;

import com.atomic.demo.domain.validation.ExpiryDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExpiryDateValidator.class)
public @interface ValidExpiryDate {
    String message() default "유효 기간은 유효한 MMYY 형식이어야 하며, 유효 기간이 만료된 카드를 사용할 수 없습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

