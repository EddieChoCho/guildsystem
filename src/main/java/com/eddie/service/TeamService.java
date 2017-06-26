package com.eddie.service;

import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.TeamType;

import java.util.List;

public interface TeamService extends CrudService<Team, Long>{

    Team findOneByLeader(User user);

    List<Team> findAllByType(TeamType teamType);

    Team createATeam(String name, User teamLeader, List<User> teamMembers);
}
