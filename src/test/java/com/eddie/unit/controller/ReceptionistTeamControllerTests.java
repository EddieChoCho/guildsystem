package com.eddie.unit.controller;

import com.eddie.controller.ReceptionistTeamController;
import com.eddie.exception.GuildSystemException;
import com.eddie.model.Team;
import com.eddie.model.pojo.GuildManager;
import com.eddie.response.impl.DataResponse;
import com.eddie.unit.fake.service.FakeReceptionistTeamServiceImpl;
import com.eddie.unit.fake.service.FakeUserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

/**
 * Created by EddieChoCho on 2017/7/14.
 */
public class ReceptionistTeamControllerTests {

    private ReceptionistTeamController controller;

    @Before
    public void setUp(){
        controller = new ReceptionistTeamController(new FakeReceptionistTeamServiceImpl(null), new FakeUserService(), new DataResponse<Team>(new ObjectMapper()));
    }

    @Test
    public void testCreateTeam() throws GuildSystemException {
        JsonNode node = controller.createTeam(null, "team", Collections.singletonList(null));
        assert (node.get("msg").textValue().equals("success"));
    }

    @Test
    public void testFindTeamLeadedByUser() throws IOException, GuildSystemException {
        GuildManager manager = new GuildManager(1L,null,null,null);
        JsonNode node = controller.findTeamLeadedByUser( manager);
        ObjectMapper mapper = new ObjectMapper();
        assert node != null;
        Team team = mapper.readValue(node.get("data").toString(), Team.class);
        assert (team != null);
        assert (team.getLeader().getId().equals(manager.getId()));
    }
}
