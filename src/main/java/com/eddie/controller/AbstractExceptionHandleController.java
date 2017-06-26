package com.eddie.controller;

import com.eddie.exception.GuildSystemException;
import com.eddie.response.ExceptionResponse;
import com.eddie.response.impl.GuildSystemExceptionResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

public abstract class AbstractExceptionHandleController {

    private ExceptionResponse response;

    public AbstractExceptionHandleController(GuildSystemExceptionResponse response){
        this.response = response;
    }

    @ExceptionHandler(GuildSystemException.class)
    public JsonNode handleCustomException(GuildSystemException exception, Locale locale) {
        return response.packResponse(exception, locale);
    }
}
