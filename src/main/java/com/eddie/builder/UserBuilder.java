package com.eddie.builder;

import com.eddie.model.User;
import com.eddie.model.enums.Role;

public class UserBuilder  {
    private String name;
    private String email;
    private String password;
    private Role role;

    public UserBuilder(){
    }
    public UserBuilder(String name, String email, String password, Role role){
        this.name = name;
        this.email =email;
        this.password = password;
        this.role = role;
    }
    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public User build(){
        User user = new User(name,email,password,role);
        this.cleanBuilder();
        return user;
    }
    private void cleanBuilder(){
        this.name = null;
        this.email = null;
        this.password = null;
        this.role = null;
    }
}
