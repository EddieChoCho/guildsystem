package com.eddie.service.impl;

import com.eddie.builder.UserBuilder;
import com.eddie.exception.AuthException;
import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.AbstractUserRepository;
import com.eddie.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService{

    private AbstractUserRepository repository;

    private UserBuilder builder;

    @Autowired
    public AuthServiceImpl(AbstractUserRepository repository, UserBuilder builder){
        this.repository = repository;
        this.builder = builder;
    }

    @Override
    public void login(HttpSession session, String email, String password) throws GuildSystemException {
        User user = repository.findOneByEmail(email);
        this.checkUser(user);
        this.checkPasswordIsCorrect(user.getPassword(), password);
        this.login(session, user);
    }

    private void checkUser(User user) throws AuthException {
        if(user == null){
            throw new AuthException("");
        }
    }

    private void checkPasswordIsCorrect(String password, String input) throws AuthException {
        if(!password.equals(input)){
            throw new AuthException("");
        }
    }

    private void login(HttpSession session, User user) {
        session.setAttribute("user",user);
    }

    public void logout(HttpSession session) {
        session.removeAttribute("user");
    }

    public void register(String name, String email, String password, String confirm, Role role) throws GuildSystemException {
        this.checkEmail(email);
        this.checkPasswordIsCorrect(password, confirm);
        User user = builder.setEmail(name).setEmail(email).setPassword(password).setRole(role).build();
        repository.save(user);
    }

    private void checkEmail(String email) throws GuildSystemException {
        User user = repository.findOneByEmail(email);
        if(user != null){
            throw new AuthException("user.email.hasBeenUsed");
        }
    }
}
