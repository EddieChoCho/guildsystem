package com.eddie.repository;

import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.TeamType;

import java.util.List;

public interface AbstractTeamRepository {

    Team save(Team team);

    Team findOne(Long id);

    Team findOneByLeaderId(Long userId);

    List<Team> findAllByType(TeamType teamType);

    void delete(Team team);
}
