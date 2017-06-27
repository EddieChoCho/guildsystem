package com.eddie.controller;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.response.impl.DataResponse;
import com.eddie.response.impl.GuildSystemExceptionResponse;
import com.eddie.service.TeamService;
import com.eddie.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("teams")
    public JsonNode createTeam(T user, @RequestParam(value = "name") String name, @RequestParam(value = "members") List<Long> idList) throws GuildSystemException {
        List<User> members = userService.findAllByIdIn(idList);
        teamService.createATeam(name, user, members);
        return teamResponse.messageResponse();
    }

    @GetMapping("teams")
    public JsonNode findTeamLeadedByUser(User user) throws GuildSystemException {
        Team team = teamService.findOneByLeader(user);
        return teamResponse.packResponse(team);
    }
}
