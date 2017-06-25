package com.eddie.exception;

public class RoleException extends GuildSystemException {
    public RoleException(String key) {
        super(key);
    }

    @Override
    public Integer getCode(){
        return 500;
    }
}
