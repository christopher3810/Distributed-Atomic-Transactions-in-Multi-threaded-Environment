package com.atomic.demo.application.validation;


import com.atomic.demo.application.validation.annotation.OptionalPattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Pattern;

public class OptionalPatternValidator implements ConstraintValidator<OptionalPattern, Optional<String>> {
    private Pattern pattern;

    @Override
    public void initialize(OptionalPattern annotation) {
        pattern = Pattern.compile(annotation.regexp());
    }

    @Override
    public boolean isValid(Optional<String> value, ConstraintValidatorContext context) {
        if (value == null || !value.isPresent()) {
            // 여기서는 Optional이 null이거나 값이 없는 경우에 유효하다고 가정합니다.
            // 필요에 따라 이 로직을 수정하세요.
            return true;
        }

        return value.isEmpty() || pattern.matcher(value.get()).matches();
    }
}
