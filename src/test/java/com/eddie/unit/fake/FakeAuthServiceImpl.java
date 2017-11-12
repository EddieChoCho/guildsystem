package com.eddie.unit.fake;

import com.eddie.builder.UserBuilder;
import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.UserRepository;
import com.eddie.service.impl.AuthServiceImpl;

import javax.servlet.http.HttpSession;

/**
 * Created by EddieChoCho on 2017/11/13.
 */
//TODO: Need to be tested.
public class FakeAuthServiceImpl extends AuthServiceImpl {
    public FakeAuthServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User register(String name, String email, String password, String confirm, Role role) throws GuildSystemException {
        User user = new UserBuilder().setName(name).setEmail(email).setPassword(password).setRole(role).build();
        return user;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) throws GuildSystemException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    @Override
    public HttpSession storeUserInSession(HttpSession session, User user) throws GuildSystemException {
        return session;
    }

    @Override
    public HttpSession removeUserFromSession(HttpSession session) {
        return session;
    }
}
