package com.eddie.factory;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.model.enums.BasicRole;
import com.eddie.model.enums.Role;
import com.eddie.model.interfaces.Player;
import com.eddie.model.pojo.Leader;
import com.eddie.model.pojo.Member;
import org.springframework.stereotype.Component;

@Component
public class PlayerFactory {

    private RoleCheckHelper validator;

    public PlayerFactory( RoleCheckHelper validator){
        this.validator = validator;
    }

    public Player providePlayer(User user) throws GuildSystemException {
        validator.roleChecking(user.getBasicRole(), BasicRole.PLAYER);
        Member member = new Member(user);
        return member;
    }

    public Member provideMember(User user) throws GuildSystemException {
        validator.roleChecking(user.getRole(), Role.MANAGER);
        Member member = new Member(user);
        return member;
    }

    public Leader provideLeader(User user) throws GuildSystemException {
        validator.roleChecking(user.getRole(), Role.LEADER);
        Leader leader = new Leader(user);
        return leader;
    }

}
