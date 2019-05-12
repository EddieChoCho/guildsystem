package com.eddie.integration.om;

import com.eddie.model.ExperimentalTeam;
import com.eddie.model.User;
import com.eddie.model.enums.TeamType;

public class ExperimentalTeamOM {

    public static ExperimentalTeam newExperimentalTeam(String name, TeamType type, User advisor){
        return ExperimentalTeam.ExperimentalTeamBuilder()
                .name(name)
                .type(type)
                .advisor(advisor)
                .build();
    }
}
