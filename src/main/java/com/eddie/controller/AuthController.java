package com.eddie.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by ASUS on 2017/6/17.
 */
@RestController
@RequestMapping("/rest/auth")
public class AuthController {
    @Autowired
    private ObjectMapper mapper;
    @GetMapping("/")
    public JsonNode helloWorld(){
        return mapper.createObjectNode().put("message","hello");
    }
}
