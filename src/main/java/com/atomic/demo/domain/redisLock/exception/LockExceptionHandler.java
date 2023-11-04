package com.atomic.demo.domain.redisLock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LockExceptionHandler {

    @ExceptionHandler(LockAnquisitionException.class)
    public ResponseEntity<String> handleLockAcquisitionException(LockAnquisitionException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
