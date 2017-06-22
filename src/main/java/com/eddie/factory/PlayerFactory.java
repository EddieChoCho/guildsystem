package com.eddie.factory;

import com.eddie.builder.PlayerBuilder;
import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.model.interfaces.Player;
import com.eddie.model.pojo.Leader;
import com.eddie.model.pojo.Member;

public class PlayerFactory {

    public Player providePlayer(User user) throws GuildSystemException {
        return new PlayerBuilder(user).buildPlayer();
    }

    public Member provideMember(User user) throws GuildSystemException {
        return new PlayerBuilder(user).buildMember();
    }

    public Leader provideLeader(User user) throws GuildSystemException {
        return new PlayerBuilder(user).buildLeader();
    }

}
