package com.atomic.demo.domain.lock.exception;

public class LockAcquisitionException extends Exception{
    public LockAcquisitionException(String msg){
        super(msg);
    }
}
