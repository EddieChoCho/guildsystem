package com.eddie.model.pojo;

import com.eddie.model.User;
import com.eddie.model.interfaces.NPC;

public class GuildPartner extends User implements NPC {
    public GuildPartner(User user){
        this.setId(user.getId());
        this.setName(user.getName());
        this.setEmail(user.getEmail());
        this.setRole(user.getRole());
    }
}
