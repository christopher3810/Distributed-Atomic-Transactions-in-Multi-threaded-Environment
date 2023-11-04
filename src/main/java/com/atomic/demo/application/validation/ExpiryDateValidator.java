package com.atomic.demo.application.validation;

import com.atomic.demo.application.validation.annotation.ValidExpiryDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.util.Pair;

/**
 * 카드의 유효 기간을 검증합니다 (MMYY 형식).
 */
public class ExpiryDateValidator implements ConstraintValidator<ValidExpiryDate, String> {
    private static final int EXPIRY_DATE_LENGTH = 4;
    private static final int MAX_MONTH = 12;
    private static final int MIN_MONTH = 1;

    private int currentYearLastTwoDigits;
    private int currentMonth;

    @Override
    public void initialize(ValidExpiryDate constraintAnnotation) {
        LocalDate currentDate = LocalDate.now();
        currentYearLastTwoDigits = currentDate.getYear() % 100;
        currentMonth = currentDate.getMonthValue();
    }

    /**
     * 주어진 만료 날짜의 유효성을 확인합니다.
     *
     * @param value   MMYY 형식의 만료 날짜 값.
     * @param context 제약 조건 평가의 컨텍스트.
     * @return 유효한 만료 날짜면 true, 그렇지 않으면 false.
     *
     * 유효성 규칙:
     * - 월(MM)은 value를 100으로 나눈 값으로 얻습니다.
     * - 연도(YY)는 value를 100으로 나눈 나머지로 얻습니다.
     * - 월이 1~12 사이이며, 연도가 현재 연도의 마지막 두 자리수보다 크거나 같아야 합니다.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
            .filter(v -> v.length() == EXPIRY_DATE_LENGTH)
            .map(this::parseDate)
            .map(this::isValidDate)
            .orElse(false);
    }


    /**
     * 날짜 파싱 메서드: 주어진 Integer에서 월과 연도 정보 추출
     */
    private Pair<Integer, Integer> parseDate(String date) {
        try {
            int month = Integer.parseInt(date.substring(0, 2));
            int year = Integer.parseInt(date.substring(2, 4));
            return Pair.of(month, year);
        } catch (NumberFormatException e) {
            return Pair.of(-1, -1);
        }
    }

    /**
     * 날짜 유효성 검사 메서드: 월과 연도가 유효한 범위에 있는지 확인
     */
    private boolean isValidDate(Pair<Integer, Integer> datePair) {
        int month = datePair.getFirst();
        int year = datePair.getSecond();

        if (isCurrentYear(year)) {
            return isMonthValidForCurrentYear(month); //eariy Return
        }

        if (isYearInFuture(year)) {
            return isMonthInRange(month);
        }

        return false;
    }

    private boolean isYearInFuture(int year) {
        return year > currentYearLastTwoDigits;
    }

    private boolean isCurrentYear(int year) {
        return year == currentYearLastTwoDigits;
    }

    private boolean isMonthValidForCurrentYear(int month) {
        return month >= currentMonth && month <= MAX_MONTH;
    }

    private boolean isMonthInRange(int month) {
        return month >= MIN_MONTH && month <= MAX_MONTH;
    }

}
