package com.eddie.exception;

public class UserException extends GuildSystemException {
    public UserException(String key) {
        super(key);
    }

    @Override
    public Integer getCode(){
        return 550;
    }
}
