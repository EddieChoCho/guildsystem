package com.eddie.service.impl;

import com.eddie.model.Team;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.TeamRepository;
import com.eddie.service.TeamService;

import java.util.List;

public abstract class AbstractTeamServiceImpl implements TeamService {

    private TeamRepository repository;

    public AbstractTeamServiceImpl(TeamRepository repository){
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
