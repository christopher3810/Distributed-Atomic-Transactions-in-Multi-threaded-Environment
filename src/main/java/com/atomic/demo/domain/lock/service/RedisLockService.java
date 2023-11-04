package com.atomic.demo.domain.lock.service;

import com.atomic.demo.domain.lock.exception.LockAcquisitionException;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RedisLockService {
    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public Mono<Boolean> acquireLock(String lockKey) {
        return reactiveRedisTemplate.opsForValue()
            .setIfAbsent(lockKey, "lock", Duration.ofSeconds(100)) // 30초 후 자동 해제
            .flatMap(acquired -> {
                if (Boolean.TRUE.equals(acquired)) {
                    return Mono.just(true);
                } else {
                    return Mono.error(new LockAcquisitionException("Unable to acquire lock: " + lockKey));
                }
            });
    }

    public Mono<Boolean> releaseLock(String lockKey) {
        return reactiveRedisTemplate.opsForValue().delete(lockKey);
    }
}