package com.eddie.exception;

public abstract class GuildSystemException extends Exception {
    GuildSystemException(String key){
        super(key);
    }
}
