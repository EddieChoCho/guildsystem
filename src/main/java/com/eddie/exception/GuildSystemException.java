package com.eddie.exception;

import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.criteria.CriteriaBuilder;

public abstract class GuildSystemException extends Exception {
    GuildSystemException(String key){
        super(key);
    }

    public Integer getCode(){
        return 440;
    }
}
