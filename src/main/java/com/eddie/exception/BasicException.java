package com.eddie.exception;

public abstract class BasicException extends Exception {
    BasicException(String key){
        super(key);
    }
}
