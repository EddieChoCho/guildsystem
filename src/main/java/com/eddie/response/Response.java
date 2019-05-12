package com.eddie.response;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface Response<T> {

    JsonNode messageResponse();

    JsonNode messageResponse(String message);

    JsonNode packResponse(T t);

    JsonNode packResponse(List<T> tList);
}
