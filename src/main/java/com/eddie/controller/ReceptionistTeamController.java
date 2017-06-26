package com.eddie.controller;

import com.eddie.model.Team;
import com.eddie.model.pojo.GuildManager;
import com.eddie.response.impl.DataResponse;
import com.eddie.response.impl.GuildSystemExceptionResponse;
import com.eddie.service.UserService;
import com.eddie.service.impl.ReceptionistTeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/receptionist/")
public class ReceptionistTeamController extends AbstractTeamController<GuildManager>{

    @Autowired
    public ReceptionistTeamController(ReceptionistTeamServiceImpl teamService, UserService userService, DataResponse<Team> teamResponse, GuildSystemExceptionResponse exceptionResponse){
        super(teamService, userService, teamResponse, exceptionResponse);
    }

}
