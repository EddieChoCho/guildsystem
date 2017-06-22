package com.eddie.model.pojo;

import com.eddie.model.enums.Role;

public class GuildManager extends GuildPartner {
    public GuildManager(Long id, String name, String email, Role role) {
        super(id, name, email, role);
    }
}
