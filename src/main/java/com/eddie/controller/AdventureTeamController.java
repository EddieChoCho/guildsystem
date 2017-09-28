package com.eddie.controller;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.Team;
import com.eddie.model.pojo.Leader;
import com.eddie.response.impl.DataResponse;
import com.eddie.service.UserService;
import com.eddie.service.impl.AdventureTeamServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/adventure/")
public class AdventureTeamController extends AbstractTeamController<Leader> {

    @Autowired
    public AdventureTeamController(AdventureTeamServiceImpl teamService, UserService userService, DataResponse<Team> teamResponse){
        super(teamService, userService, teamResponse);
    }

    @PostMapping("teams")
    public JsonNode createTeam(Leader leader, @RequestParam(value = "name") String name, @RequestParam(value = "members") List<Long> idList) throws GuildSystemException {
        return super.createTeam(leader, name, idList);
    }

    @GetMapping("teams")
    public JsonNode findTeamLeadedByUser(Leader leader) throws GuildSystemException {
        return super.findTeamLeadedByUser(leader);
    }
}
