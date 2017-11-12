package com.eddie.service.impl;

import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReceptionistTeamServiceImpl extends AbstractTeamServiceImpl{

    @Autowired
    public ReceptionistTeamServiceImpl(TeamRepository repository){
        super(repository);
    }

    @Override
    public Team createATeam(String name, User teamLeader, List<User> teamMembers) {
        Team team = new Team(name, TeamType.RECEPTIONIST,teamLeader,teamMembers);
        return this.add(team);
    }
}
