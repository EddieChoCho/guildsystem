package com.eddie.controller;

import com.eddie.builder.UserBuilder;
import com.eddie.exception.AuthException;
import com.eddie.exception.GuildSystemException;
import com.eddie.exception.UserException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.response.GuildSystemExceptionResponse;
import com.eddie.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/users/")
public class UserController extends AbstractExceptionHandleController{

    private UserService userService;

    private ObjectMapper mapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper mapper, GuildSystemExceptionResponse exceptionResponse) {
        super(exceptionResponse);
        this.userService = userService;
        this.mapper = mapper;

    }


}
