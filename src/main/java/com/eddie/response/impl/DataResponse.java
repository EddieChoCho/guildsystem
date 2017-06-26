package com.eddie.response.impl;

import com.eddie.response.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class DataResponse<T> implements Response<T> {

    private String message = "success";

    private Integer code = 200;

    private ObjectMapper mapper;

    public DataResponse(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public JsonNode messageResponse() {
        return mapper.createObjectNode().put("msg",message);
    }

    @Override
    public JsonNode messageResponse(String message) {
        return mapper.createObjectNode().put("msg",message);
    }

    @Override
    public JsonNode packResponse(T t) {
        ObjectNode response = mapper.createObjectNode();
        response.put("status",200);
        response.set("data",mapper.valueToTree(t));
        return response;
    }

    @Override
    public JsonNode packResponse(List<T> ts) {
        ObjectNode response = mapper.createObjectNode();
        response.put("status",200);
        response.set("list",mapper.valueToTree(ts));
        return response;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
