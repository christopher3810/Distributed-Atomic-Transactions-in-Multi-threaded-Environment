package com.atomic.demo.domain.redisLock.exception;

public class LockAnquisitionException extends Exception{

    public LockAnquisitionException (String msg){
        super(msg);
    }

}
