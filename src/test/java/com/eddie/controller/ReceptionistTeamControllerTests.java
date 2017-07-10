package com.eddie.controller;

import com.eddie.builder.NpcBuilder;
import com.eddie.exception.GuildSystemException;
import com.eddie.mock.FakeAbstractTeamRepository;
import com.eddie.mock.FakeUserRepository;
import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import com.eddie.model.pojo.GuildManager;
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
public class ReceptionistTeamControllerTests {
    private ReceptionistTeamController controller;
    private FakeAbstractTeamRepository mockRepository;
    private GuildManager manager;
    private User partner;

    @Before
    public void setUp() throws GuildSystemException {
        AbstractUserRepository userRepository = new FakeUserRepository();
        mockRepository = new FakeAbstractTeamRepository();
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        ObjectMapper mapper = new ObjectMapper();
        DataResponse<Team> response = new DataResponse<>(mapper);
        ReceptionistTeamServiceImpl teamService = new ReceptionistTeamServiceImpl(mockRepository);
        controller = new ReceptionistTeamController(teamService, userService, response);
        User user = new User("manager", "manager@email", "password", Role.MANAGER);
        manager = new NpcBuilder(user).buildGuildManager();
        partner = new User("partner", "partner@email", "password", Role.PARTNER);
        userService.add(manager);
        userService.add(partner);
    }

    @Test
    public void testCreateTeam() throws GuildSystemException {
        JsonNode node = controller.createTeam(manager, "team", Arrays.asList(partner.getId()));
        assert (node.get("msg").textValue().equals("success"));

    }

    @Test
    public void testCreateTeamCouldStoreTeamData() throws GuildSystemException {
        controller.createTeam(manager, "team", Arrays.asList(partner.getId()));
        Team teem =mockRepository.teamList.get(0);
        assert (teem.getLeader().equals(manager));
        assert (teem.getName().equals("team"));
        assert (teem.getMembers().contains(partner));
        assert (teem.getType().equals(TeamType.RECEPTIONIST));
    }

    @Test
    public void testFindTeamLeadedByUser() throws IOException, GuildSystemException {
        Team newTeam = new Team("team",TeamType.RECEPTIONIST,manager,Arrays.asList(partner));
        mockRepository.teamList.add(newTeam);
        JsonNode node = controller.findTeamLeadedByUser(manager);
        ObjectMapper mapper = new ObjectMapper();
        assert node != null;
        Team team = mapper.readValue(node.get("data").toString(), Team.class);
        assert (team.getName().equals(newTeam.getName()));
        assert (team.getLeader().getId().equals(newTeam.getLeader().getId()));
        assert (team.getMembers().size() == newTeam.getMembers().size());
        assert (team.getMembers().get(0).getId().equals(newTeam.getMembers().get(0).getId()));
        assert (team.getType().equals(newTeam.getType()));
    }
}

