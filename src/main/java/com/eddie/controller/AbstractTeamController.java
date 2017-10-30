package com.eddie.controller;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.response.impl.DataResponse;
import com.eddie.service.TeamService;
import com.eddie.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public abstract class AbstractTeamController<T extends User>{

    private TeamService teamService;

    private UserService userService;

    private DataResponse<Team> teamResponse;

    public AbstractTeamController(TeamService teamService, UserService userService, DataResponse<Team> teamResponse) {
        this.teamService = teamService;
        this.userService = userService;
        this.teamResponse = teamResponse;
    }

    public JsonNode createTeam(T user, String name, List<Long> idList) throws GuildSystemException {
        List<User> members = userService.findAllByIdIn(idList);
        teamService.createATeam(name, user, members);
        return teamResponse.messageResponse();
    }

    public JsonNode findTeamLeadedByUser(T user) throws GuildSystemException {
        Team team = teamService.findOneByLeaderId(user.getId());
        return teamResponse.packResponse(team);
    }
}
