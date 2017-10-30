package com.eddie.service.impl;

import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.AbstractTeamRepository;
import com.eddie.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractTeamServiceImpl implements TeamService {

    private AbstractTeamRepository repository;

    @Autowired
    public AbstractTeamServiceImpl(AbstractTeamRepository repository){
        this.repository = repository;
    }

    @Override
    public Team add(Team team) {
        return repository.save(team);
    }

    @Override
    public Team edit(Team team) {
        return repository.save(team);
    }

    @Override
    public Team findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Team team) {
        repository.delete(team);
    }

    @Override
    public Team findOneByLeaderId(Long leaderId){
        return repository.findOneByLeaderId(leaderId);
    }

    @Override
    public List<Team> findAllByType(TeamType teamType){
        return repository.findAllByType(teamType);
    }
}
