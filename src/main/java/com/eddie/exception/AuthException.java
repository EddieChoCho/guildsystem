package com.eddie.exception;

public class AuthException extends GuildSystemException {
    public AuthException(String key) {
        super(key);
    }

    @Override
    public Integer getCode(){
        return 600;
    }
}
