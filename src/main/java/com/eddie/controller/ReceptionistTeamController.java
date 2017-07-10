package com.eddie.controller;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.Team;
import com.eddie.model.pojo.GuildManager;
import com.eddie.model.pojo.Leader;
import com.eddie.response.impl.DataResponse;
import com.eddie.service.UserService;
import com.eddie.service.impl.ReceptionistTeamServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/receptionist/")
public class ReceptionistTeamController extends AbstractTeamController<GuildManager> {

    @Autowired
    public ReceptionistTeamController(ReceptionistTeamServiceImpl teamService, UserService userService, DataResponse<Team> teamResponse){
        super(teamService, userService, teamResponse);
    }

    @PostMapping("teams")
    public JsonNode createTeam(GuildManager manager, @RequestParam(value = "name") String name, @RequestParam(value = "members") List<Long> idList) throws GuildSystemException {
        return super.createTeam(manager, name, idList);
    }

    @GetMapping("teams")
    public JsonNode findTeamLeadedByUser(GuildManager manager) throws GuildSystemException {
        return super.findTeamLeadedByUser(manager);
    }
}
