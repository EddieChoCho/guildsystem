package com.eddie.model;

import com.eddie.model.enums.TeamType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

    @Builder
    public Team(String name, TeamType type, User leader, List<User> members){
        this.name = name;
        this.type = type;
        this.leader = leader;
        this.members = members;
    }

}
