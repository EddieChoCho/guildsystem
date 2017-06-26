package com.eddie.response;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface Response<T> {

    public JsonNode messageResponse();

    public JsonNode messageResponse(String message);

    public JsonNode packResponse(T t);

    public JsonNode packResponse(List<T> tList);
}
