package com.eddie.builder;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.enums.BasicRole;
import com.eddie.model.enums.Role;
import com.eddie.model.interfaces.Person;
import com.eddie.model.interfaces.Player;
import com.eddie.model.pojo.Leader;
import com.eddie.model.pojo.Member;

public class PlayerBuilder extends AbstractPersonBuilder {
    private Long id;
    private String name;
    private String email;
    private Role role;

    public PlayerBuilder(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.email = person.getEmail();
        this.role = person.getRole();
    }

    public Player buildPlayer() throws GuildSystemException {
        roleChecking(role.getBasicRole(), BasicRole.PLAYER);
        Member member = new Member(id,name,email,role);
        return member;
    }

    public Leader buildLeader() throws GuildSystemException {
        roleChecking(this.role, Role.LEADER);
        Leader leader = new Leader(id,name,email,role);
        return leader;
    }

    public Member buildMember() throws GuildSystemException {
        roleChecking(this.role, Role.MEMBER);
        Member member = new Member(id,name,email,role);
        return member;
    }


}
