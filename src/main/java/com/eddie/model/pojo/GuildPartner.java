package com.eddie.model.pojo;

import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.interfaces.NPC;

public class GuildPartner extends User implements NPC {
    public GuildPartner(Long id, String name, String email, Role role){
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setRole(role);
    }
}
