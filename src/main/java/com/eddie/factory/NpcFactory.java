package com.eddie.factory;

import com.eddie.builder.NpcBuilder;
import com.eddie.exception.BasicException;
import com.eddie.exception.RoleException;
import com.eddie.model.User;
import com.eddie.model.enums.BasicRole;
import com.eddie.model.enums.Role;
import com.eddie.model.interfaces.NPC;
import com.eddie.model.pojo.GuildManager;
import com.eddie.model.pojo.GuildPartner;

public class NpcFactory {

    public NPC factorNpc(User user) throws BasicException {
        if(!user.getRole().getBasicRole().equals(BasicRole.NPC)){
            throw new RoleException("You are not a NPC!");
        }
        return new NpcBuilder(user).buildNpc();
    }

    public GuildPartner factorGuildPartner(User user) throws BasicException {
        if(!user.getRole().equals(Role.PARTNER)){
            throw new RoleException("You are not a Partner!");
        }
        return new NpcBuilder(user).buildGuildPartner();
    }

    public GuildManager factorGuildManager(User user) throws BasicException {
        if(!user.getRole().equals(Role.MEMBER)){
            throw new RoleException("You are not a Manager!");
        }
        return new NpcBuilder(user).buildGuildManager();
    }
}
