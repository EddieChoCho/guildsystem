package com.eddie.factory;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.model.enums.BasicRole;
import com.eddie.model.enums.Role;
import com.eddie.model.interfaces.NPC;
import com.eddie.model.pojo.GuildManager;
import com.eddie.model.pojo.GuildPartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NpcFactory {

    private RoleCheckHelper validator;

    @Autowired
    public NpcFactory(RoleCheckHelper validator){
        this.validator = validator;
    }

    public NPC provideNpc(User user) throws GuildSystemException {
        validator.roleChecking(user.getBasicRole(), BasicRole.NPC);
        return new GuildPartner(user);
    }

    public GuildPartner provideGuildPartner(User user) throws GuildSystemException {
        validator.roleChecking(user.getRole(), Role.PARTNER);
        return new GuildPartner(user);
    }

    public GuildManager provideGuildManager(User user) throws GuildSystemException {
        validator.roleChecking(user.getRole(), Role.MANAGER);
        return new GuildManager(user);
    }
}
