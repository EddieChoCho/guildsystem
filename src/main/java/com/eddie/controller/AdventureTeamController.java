package com.eddie.controller;

import com.eddie.model.Team;
import com.eddie.model.pojo.Leader;
import com.eddie.response.impl.DataResponse;
import com.eddie.response.impl.GuildSystemExceptionResponse;
import com.eddie.service.UserService;
import com.eddie.service.impl.AdventureTeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/adventure/")
public class AdventureTeamController extends TeamController<Leader>{

    @Autowired
    public AdventureTeamController(AdventureTeamServiceImpl teamService, UserService userService, DataResponse<Team> teamResponse){
        super(teamService, userService, teamResponse);
    }
}
