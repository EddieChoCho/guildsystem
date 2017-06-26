package com.eddie.model;

import com.eddie.model.enums.TeamType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Team extends AbstractEntity {

    @NotNull
    @Column(name="name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private TeamType type;

    @OneToOne
    private User leader;

    @OneToMany
    private List<User> members;

    public Team(){

    }

    public Team(String name, TeamType type, User leader, List<User> members){
        this.name = name;
        this.type = type;
        this.leader = leader;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamType getType() {
        return type;
    }

    public void setType(TeamType type) {
        this.type = type;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
