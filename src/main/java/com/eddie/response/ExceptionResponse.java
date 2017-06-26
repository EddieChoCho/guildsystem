package com.eddie.response;
import com.eddie.exception.GuildSystemException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Locale;

public interface ExceptionResponse<T extends Exception> {
    public JsonNode packResponse(T exception, Locale locale);
}
