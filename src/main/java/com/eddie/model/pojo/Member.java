package com.eddie.model.pojo;

import com.eddie.model.User;
import com.eddie.model.interfaces.Player;

public class Member extends User implements Player {
    public Member(User user){
        this.setId(user.getId());
        this.setName(user.getName());
        this.setEmail(user.getEmail());
        this.setRole(user.getRole());
    }
}
