package com.eddie.service;


import com.eddie.exception.GuildSystemException;
import com.eddie.model.enums.Role;

import javax.servlet.http.HttpSession;

public interface AuthService {

    void register(String name, String email, String password, String confirm, Role role) throws GuildSystemException;

    void login(HttpSession session, String email, String password) throws GuildSystemException;

    void logout(HttpSession session);
}
