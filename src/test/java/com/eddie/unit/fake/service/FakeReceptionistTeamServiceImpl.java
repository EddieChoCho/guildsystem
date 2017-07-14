package com.eddie.unit.fake.service;

import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.AbstractTeamRepository;
import com.eddie.service.impl.ReceptionistTeamServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/7/14.
 */
public class FakeReceptionistTeamServiceImpl extends ReceptionistTeamServiceImpl {

    public FakeReceptionistTeamServiceImpl(AbstractTeamRepository repository) {
        super(repository);
    }

    @Override
    public Team add(Team team) {
        return team;
    }

    @Override
    public Team edit(Team team) {
        return team;
    }

    @Override
    public Team findById(Long id) {
        Team team = new Team(null,null,null,null);
        team.setId(id);
        return team;
    }

    @Override
    public void delete(Team team) {

    }

    @Override
    public Team findOneByLeader(User user){
        return new Team(null,null,user,null);
    }

    @Override
    public List<Team> findAllByType(TeamType teamType){
        List<Team> teamList = new ArrayList<>();
        teamList.add(new Team(null,teamType,null,null));
        return teamList;
    }

    @Override
    public Team createATeam(String name, User teamLeader, List<User> teamMembers) {
        return new Team(name,TeamType.RECEPTIONIST,teamLeader,teamMembers);
    }
}
