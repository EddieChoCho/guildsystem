package com.eddie.model.pojo;

import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.interfaces.Player;

public class Member extends User implements Player {
    public Member(Long id, String name, String email, Role role){
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setRole(role);
    }
}
