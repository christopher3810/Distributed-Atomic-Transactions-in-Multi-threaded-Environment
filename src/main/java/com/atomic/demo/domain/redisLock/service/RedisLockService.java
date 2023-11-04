package com.atomic.demo.domain.redisLock.service;

import com.atomic.demo.domain.redisLock.exception.LockAnquisitionException;
import com.atomic.demo.domain.redisLock.model.LockSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RedisLockService {
    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    /**
     * cardNumber 에 대한 lock을 호출하기 위한 메서드.
     * 1. lock 키 , 값 생성
     * 2. 없으면 넣고 있으면 error 발생
     */
    public Mono<Boolean> acquireLock(String cardNumber) {
        String lockKey =  LockSettings.DEFAULT.lockPrefix() + cardNumber;
        String lockValue = LockSettings.DEFAULT.lockedByCardNumber(cardNumber);

        return reactiveRedisTemplate.opsForValue()
            .setIfAbsent(lockKey, lockValue, LockSettings.DEFAULT.lockTtl())
            .filter(Boolean.TRUE::equals)
            .switchIfEmpty(Mono.error(new LockAnquisitionException("Unable to acquire lock Because Lock already Exist : " + lockKey)))
            .thenReturn(true);
    }

    public Mono<Boolean> releaseLock(String cardNumber) {
        String lockKey = LockSettings.DEFAULT.lockPrefix() + cardNumber;
        return reactiveRedisTemplate.opsForValue().delete(lockKey)
            .doOnSuccess(result -> {/* 락 해제 성공 로그 등 처리 */})
            .doOnError(error -> {/* 락 해제 실패 로그 등 처리 */});
    }
}