package com.eddie.builder;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.enums.BasicRole;
import com.eddie.model.enums.Role;
import com.eddie.model.interfaces.NPC;
import com.eddie.model.interfaces.Person;
import com.eddie.model.pojo.GuildManager;
import com.eddie.model.pojo.GuildPartner;

public class NpcBuilder extends AbstractPersonBuilder{

    private Long id;
    private String name;
    private String email;
    private Role role;

    public NpcBuilder(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.email = person.getEmail();
        this.role = person.getRole();
    }

    public NPC buildNpc() throws GuildSystemException {
        roleChecking(this.role.getBasicRole(), BasicRole.NPC);
        GuildPartner partner = new GuildPartner(id,name,email,role);
        return partner;
    }

    public GuildPartner buildGuildPartner() throws GuildSystemException {
        roleChecking(this.role, Role.PARTNER);
        GuildPartner partner = new GuildPartner(id,name,email,role);
        return partner;
    }

    public GuildManager buildGuildManager() throws GuildSystemException {
        roleChecking(this.role, Role.MANAGER);
        GuildManager manager = new GuildManager(id,name,email,role);
        return manager;
    }
}
