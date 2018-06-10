package com.vishnuk.allocationservice.exceptionhandler;

public class RedisError extends RuntimeException{

    public RedisError(String message) {
        super(message);
    }
}
