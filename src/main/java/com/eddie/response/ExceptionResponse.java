package com.eddie.response;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Locale;

public interface ExceptionResponse<T extends Exception> {
    JsonNode packResponse(T exception, Locale locale);
}
