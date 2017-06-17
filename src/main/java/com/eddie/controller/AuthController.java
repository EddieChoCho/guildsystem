package com.eddie.controller;

import com.eddie.builder.UserBuilder;
import com.eddie.exception.AuthException;
import com.eddie.exception.BasicException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/auth/")
public class AuthController {
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private UserService userService;
    @GetMapping()
    public JsonNode helloWorld(){
        return mapper.createObjectNode().put("message","hello");
    }

    @PostMapping("player/register/")
    public JsonNode registerPlayer(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "confirm") String confirm) throws BasicException{
        this.register(name,email,password,confirm, Role.MEMBER);
        return mapper.createObjectNode().put("message","success");
    }

    @PostMapping("npc/register/")
    public JsonNode registerNpc(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "confirm") String confirm) throws BasicException{
        this.register(name,email,password,confirm, Role.PARTNER);
        return mapper.createObjectNode().put("message","success");
    }

    @PostMapping("login/")
    public JsonNode login(@RequestParam(value = "email") String email,
                          @RequestParam(value = "password") String password,
                          HttpSession session) throws BasicException{
        User user = userService.findOneByEmail(email);
        userService.checkPassword(user, password);
        this.login(session,user);
        return mapper.createObjectNode().put("message","success");
    }

    @GetMapping("logout/")
    public JsonNode logout(User user, HttpSession session) throws BasicException{
        this.logout(session);
        return mapper.createObjectNode().put("message","success");
    }

    private void register(String name, String email, String password, String confirm, Role role) throws BasicException {
        if(!password.equals(confirm)){
            throw new AuthException("The password is not match the confirm");
        }
        User user = new UserBuilder(name,email,password, role).build();
        userService.add(user);
    }

    private void login(HttpSession session, User user){
        session.setAttribute("user",user);
    }

    private void logout(HttpSession session){
        session.removeAttribute("user");
    }
}
