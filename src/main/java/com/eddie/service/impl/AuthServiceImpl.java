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

    @Autowired
    public AuthServiceImpl(AbstractUserRepository repository){
        this.repository = repository;
    }

    @Override
    public HttpSession login(HttpSession session, String email, String password) throws GuildSystemException {
        User user = repository.findOneByEmailAndPassword(email,password);
        if(user == null){
            this.loginValidation(email, password);
        }
        return this.login(session, user);
    }

    private boolean checkUser(User user) throws AuthException {
        return user != null;
    }

    private boolean checkPasswordIsCorrect(String password, String input) throws AuthException {
        return password.equals(input);
    }

    private HttpSession login(HttpSession session, User user) {
        session.setAttribute("user",user);
        return session;
    }

    public HttpSession logout(HttpSession session) {
        session.removeAttribute("user");
        return session;
    }

    public User register(String name, String email, String password, String confirm, Role role) throws GuildSystemException {
        this.registrationValidation(email, password, confirm);
        User user = new UserBuilder().setName(name).setEmail(email).setPassword(password).setRole(role).build();
        return repository.save(user);
    }

    private boolean checkEmailHasNotBeenUsed(String email) throws GuildSystemException {
        User user = repository.findOneByEmail(email);
        return user == null;
    }

    private void registrationValidation(String email, String password, String confirm) throws GuildSystemException {
        if(!this.checkEmailHasNotBeenUsed(email)){
            throw new AuthException("auth.register.email.hasBeenUsed");
        }
        if(!this.checkPasswordIsCorrect(password, confirm)){
            throw new AuthException("auth.register.password.notConfirmed");
        }
    }

    private void loginValidation(String email, String password) throws GuildSystemException {
        User user = repository.findOneByEmail(email);
        if(!this.checkUser(user)){
            throw new AuthException("auth.login.email.hasNotBeenRegistered");
        }
        if(!this.checkPasswordIsCorrect(user.getPassword(), password)){
            throw new AuthException("auth.login.password.isNotCorrect");
        }
    }
}
