package com.eddie.model;


import com.eddie.model.enums.TeamType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "experimentId")
public class ExperimentalTeam extends Team {

    @OneToOne
    private User advisor;

    private String experiment;

    @Builder(builderMethodName ="ExperimentalTeamBuilder")
    public ExperimentalTeam(String name, TeamType type, User leader, List<User> members, User advisor, String experiment){
        super(name, type, leader, members);
        this.advisor = advisor;
        this.experiment = experiment;
    }
}
