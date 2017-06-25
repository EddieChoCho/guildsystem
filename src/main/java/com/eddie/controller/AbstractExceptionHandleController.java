package com.eddie.controller;

import com.eddie.exception.GuildSystemException;
import com.eddie.response.GuildSystemExceptionResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

public abstract class AbstractExceptionHandleController {

    private GuildSystemExceptionResponse response;

    public AbstractExceptionHandleController(GuildSystemExceptionResponse response){
        this.response = response;
    }

    @ExceptionHandler(GuildSystemException.class)
    public JsonNode handleCustomException(GuildSystemException exception, Locale locale) {
        return response.packResponse(exception, locale);
    }
}
