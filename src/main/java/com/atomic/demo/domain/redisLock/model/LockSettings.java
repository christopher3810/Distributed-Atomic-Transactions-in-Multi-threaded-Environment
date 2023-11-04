package com.atomic.demo.domain.redisLock.model;

import java.time.Duration;

public record LockSettings(String lockPrefix, Duration lockTtl) {

    public static final LockSettings DEFAULT = new LockSettings(
        "payment_lock_",
        Duration.ofSeconds(100)
    );

    // 카드 번호에 따른 잠금 문자열을 생성하는 메소드
    public String lockedByCardNumber(String cardNumber) {
        return "LOCKED_BY_" + cardNumber;
    }
}