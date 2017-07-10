package com.eddie.controller;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.enums.Role;
import com.eddie.response.impl.GuildSystemExceptionResponse;
import com.eddie.service.AuthService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/auth/")
public class AuthController {

    private AuthService authService;

    private ObjectMapper mapper;

    @Autowired
    public AuthController(AuthService authService, ObjectMapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @PostMapping("{role}/register/")
    public JsonNode register(@PathVariable Role role,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "confirm") String confirm) throws GuildSystemException {
        authService.register(name, email, password,confirm, role);
        return mapper.createObjectNode().put("message","success");
    }

    @PostMapping("login/")
    public JsonNode login(@RequestParam(value = "email") String email,
                          @RequestParam(value = "password") String password,
                          HttpSession session) throws GuildSystemException {
        authService.login(session, email, password);
        return mapper.createObjectNode().put("message","success");
    }

    @GetMapping("logout/")
    public JsonNode logout(HttpSession session) throws GuildSystemException {
        authService.logout(session);
        return mapper.createObjectNode().put("message","success");
    }


}
