package com.eddie.controller;

import com.eddie.builder.NpcBuilder;
import com.eddie.exception.GuildSystemException;
import com.eddie.mock.MockAbstractTeamRepository;
import com.eddie.mock.MockUserRepository;
import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import com.eddie.model.pojo.GuildManager;
import com.eddie.repository.AbstractTeamRepository;
import com.eddie.repository.AbstractUserRepository;
import com.eddie.response.impl.DataResponse;
import com.eddie.service.impl.ReceptionistTeamServiceImpl;
import com.eddie.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by EddieChoCho on 2017/6/27.
 */
public class ReceptionistAbstractTeamControllerTests {
    private ReceptionistAbstractTeamController controller;

    private GuildManager manager;
    private User partner;

    @Before
    public void setUp() throws GuildSystemException {
        AbstractUserRepository userRepository = new MockUserRepository();
        AbstractTeamRepository teamRepository = new MockAbstractTeamRepository();
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        ObjectMapper mapper = new ObjectMapper();
        DataResponse<Team> response = new DataResponse<Team>(mapper);
        ReceptionistTeamServiceImpl teamService = new ReceptionistTeamServiceImpl(teamRepository);
        controller = new ReceptionistAbstractTeamController(teamService, userService, response);
        User user = new User("manager", "manager@email", "password", Role.MANAGER);
        manager = new NpcBuilder(user).buildGuildManager();
        partner = new User("partner", "partner@email", "password", Role.PARTNER);
        userService.add(manager);
        userService.add(partner);
    }

    @Test
    public void testCreateTeam() {
        JsonNode node = null;
        try {
            node = controller.createTeam(manager, "team", Arrays.asList(partner.getId()));
        } catch (GuildSystemException e) {
            e.printStackTrace();
        }
        assert (node.get("msg").textValue().equals("success"));
    }

    @Test
    public void testFindTeamLeadedByUser() throws IOException {
        JsonNode node = null;
        try {
            controller.createTeam(manager, "team", Arrays.asList(partner.getId()));
            node = controller.findTeamLeadedByUser(manager);
        } catch (GuildSystemException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        Team team = mapper.readValue(node.get("data").toString(), Team.class);
        assert (team.getName().equals("team"));
        assert (team.getLeader().getId().equals(manager.getId()));
        assert (team.getMembers().get(0).getId().equals(partner.getId()));
        assert (team.getType().equals(TeamType.RECEPTIONIST));
    }
}
