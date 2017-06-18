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

    public NPC provideNpc(User user) throws BasicException {
        return new NpcBuilder(user).buildNpc();
    }

    public GuildPartner provideGuildPartner(User user) throws BasicException {
        return new NpcBuilder(user).buildGuildPartner();
    }

    public GuildManager provideGuildManager(User user) throws BasicException {
        return new NpcBuilder(user).buildGuildManager();
    }
}
