package com.eddie.service.impl;

import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.AbstractTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureTeamServiceImpl extends AbstractTeamServiceImpl {

    @Autowired
    public AdventureTeamServiceImpl(AbstractTeamRepository repository){
        super(repository);
    }

    @Override
    public Team createATeam(String name, User teamLeader, List<User> teamMembers) {
        Team team = new Team(name, TeamType.ADVENTURE,teamLeader,teamMembers);
        return this.add(team);
    }
}
