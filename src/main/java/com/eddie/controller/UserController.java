package com.eddie.controller;

import com.eddie.model.User;
import com.eddie.repository.UserRepository;
import com.eddie.response.impl.DataResponse;
import com.eddie.response.impl.GuildSystemExceptionResponse;
import com.eddie.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users/")
public class UserController extends AbstractExceptionHandleController{

    @Autowired
    private UserRepository repository;

    private UserService userService;

    private DataResponse<User> userResponse;

    @Autowired
    public UserController(UserService userService, DataResponse<User> userResponse, GuildSystemExceptionResponse exceptionResponse) {
        super(exceptionResponse);
        this.userService = userService;
        this.userResponse = userResponse;

    }

    @GetMapping("me/")
    public JsonNode checkUserInfo(User user){
        return userResponse.packResponse(user);
    }

    @GetMapping("me/all")
    public JsonNode checkAllUserInfo(User user){
        List<User> userList = repository.findAll();
        return userResponse.packResponse(userList);
    }


}
