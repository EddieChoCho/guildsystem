package com.eddie.model.interfaces;

import com.eddie.model.enums.Role;

/**
 * Created by ASUS on 2017/6/17.
 */
public interface Person {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getEmail();

    void setEmail(String email);

    Role getRole();

    void setRole(Role role);
}
