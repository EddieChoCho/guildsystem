package com.eddie.service;


import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;

import javax.servlet.http.HttpSession;

public interface AuthService {

    User register(String name, String email, String password, String confirm, Role role) throws GuildSystemException;

    HttpSession login(HttpSession session, String email, String password) throws GuildSystemException;

    HttpSession logout(HttpSession session);
}
