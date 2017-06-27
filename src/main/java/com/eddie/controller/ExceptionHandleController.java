package com.eddie.controller;

import com.eddie.exception.GuildSystemException;
import com.eddie.response.ExceptionResponse;
import com.eddie.response.impl.GuildSystemExceptionResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@ControllerAdvice
public class ExceptionHandleController {

    private ExceptionResponse response;

    public ExceptionHandleController(GuildSystemExceptionResponse response){
        this.response = response;
    }

    @ExceptionHandler(GuildSystemException.class)
    @ResponseBody
    public JsonNode handleCustomException(GuildSystemException exception, Locale locale) {
        return response.packResponse(exception, locale);
    }
}
