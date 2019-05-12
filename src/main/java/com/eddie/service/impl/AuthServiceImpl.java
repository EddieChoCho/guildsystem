package com.eddie.service.impl;

import com.eddie.exception.AuthException;
import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.UserRepository;
import com.eddie.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService{

    private UserRepository repository;

    @Autowired
    public AuthServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User register(String name, String email, String password, String confirm, Role role) throws GuildSystemException {
        this.registrationValidation(email, password, confirm);
        User user = User.builder().name(name).email(email).password(password).role(role).build();
        return repository.save(user);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) throws GuildSystemException {
        User user = repository.findOneByEmailAndPassword(email,password);
        if(user == null){
            this.loginValidation(email, password);
        }
        return user;
    }

    @Override
    public HttpSession storeUserInSession(HttpSession session, User user) throws GuildSystemException {
        session.setAttribute("user",user);
        return session;
    }

    @Override
    public HttpSession removeUserFromSession(HttpSession session) {
        session.removeAttribute("user");
        return session;
    }

    private void registrationValidation(String email, String password, String confirm) throws GuildSystemException {
        if(!this.checkEmailHasNotBeenUsed(email)){
            throw new AuthException("auth.register.email.hasBeenUsed");
        }
        if(!this.checkPasswordIsCorrect(password, confirm)){
            throw new AuthException("auth.register.password.notConfirmed");
        }
    }

    private boolean checkPasswordIsCorrect(String password, String input) throws AuthException {
        return password.equals(input);
    }

    private boolean checkEmailHasNotBeenUsed(String email) throws GuildSystemException {
        User user = repository.findOneByEmail(email);
        return user == null;
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

    private boolean checkUser(User user) throws AuthException {
        return user != null;
    }
}
