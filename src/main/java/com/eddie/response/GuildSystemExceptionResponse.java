package com.eddie.response;

import com.eddie.exception.GuildSystemException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class GuildSystemExceptionResponse {

    private MessageSource messageSource;

    private ObjectMapper mapper;

    @Autowired
    public GuildSystemExceptionResponse(MessageSource messageSource, ObjectMapper mapper){
        this.messageSource = messageSource;
        this.mapper = mapper;
    }

    public JsonNode packResponse(GuildSystemException exception, Locale locale){
        String message = messageSource.getMessage(exception.getMessage(), new Object[0] , locale);
        ObjectNode response = mapper.createObjectNode();
        response.put("status", exception.getCode());
        response.put("context", message);
        return response;
    }
}
