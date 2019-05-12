package com.eddie.integration.om;

import com.eddie.model.User;
import com.eddie.model.enums.Role;

public class UserOM {

    public static User newUser(){
        return User.builder().name("Gandalf").email("gandalf@mail").password("weShouldCallSomeEagles").role(Role.LEADER).build();
    }

    public static User newUser(String userName, String email, String password){
        return User.builder()
                .name(userName)
                .email(email)
                .password(password)
                .build();
    }
}
