package com.eddie.factory;

import com.eddie.builder.PlayerBuilder;
import com.eddie.exception.BasicException;
import com.eddie.exception.RoleException;
import com.eddie.model.User;
import com.eddie.model.enums.BasicRole;
import com.eddie.model.enums.Role;
import com.eddie.model.interfaces.Player;
import com.eddie.model.pojo.Leader;
import com.eddie.model.pojo.Member;

public class PlayerFactory {

    public Player factorPlayer(User user) throws BasicException{
        if(!user.getRole().getBasicRole().equals(BasicRole.PLAYER)){
            throw new RoleException("You are not a Player!");
        }
        return new PlayerBuilder(user).buildPlayer();
    }

    public Member factorMember(User user) throws BasicException{
        if(!user.getRole().equals(Role.MEMBER)){
            throw new RoleException("You are not a Member!");
        }
        return new PlayerBuilder(user).buildMember();
    }

    public Leader factorLeader(User user) throws BasicException{
        if(!user.getRole().equals(Role.LEADER)){
            throw new RoleException("You are not a Leader!");
        }
        return new PlayerBuilder(user).buildLeader();
    }

}
