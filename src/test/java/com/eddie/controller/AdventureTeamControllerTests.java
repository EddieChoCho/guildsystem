package com.eddie.controller;

import com.eddie.builder.PlayerBuilder;
import com.eddie.exception.GuildSystemException;
import com.eddie.mock.MockAbstractTeamRepository;
import com.eddie.mock.MockUserRepository;
import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import com.eddie.model.pojo.Leader;
import com.eddie.repository.AbstractTeamRepository;
import com.eddie.repository.AbstractUserRepository;
import com.eddie.response.impl.DataResponse;
import com.eddie.service.impl.AdventureTeamServiceImpl;
import com.eddie.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/6/27.
 */
public class AdventureTeamControllerTests {

    private AdventureAbstractTeamController controller;

    private Leader leader;
    private User member;

    @Before
    public void setUp() throws GuildSystemException {
        AbstractUserRepository userRepository = new MockUserRepository();
        AbstractTeamRepository teamRepository = new MockAbstractTeamRepository();
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        ObjectMapper mapper = new ObjectMapper();
        DataResponse<Team> response = new DataResponse<Team>(mapper);
        AdventureTeamServiceImpl teamService = new AdventureTeamServiceImpl(teamRepository);
        controller = new AdventureAbstractTeamController(teamService, userService, response);
        User user = new User("leader", "leader@email", "password", Role.LEADER);
        leader = new PlayerBuilder(user).buildLeader();
        member = new User("member", "member@email", "password", Role.MEMBER);
        userService.add(leader);
        userService.add(member);
    }

    @Test
    public void testCreateTeam() {
        JsonNode node = null;
        try {
            node = controller.createTeam(leader, "team", Arrays.asList(member.getId()));
        } catch (GuildSystemException e) {
            e.printStackTrace();
        }
        assert (node.get("msg").textValue().equals("success"));
    }

    @Test
    public void testFindTeamLeadedByUser() throws IOException {
        JsonNode node = null;
        try {
            controller.createTeam(leader, "team", Arrays.asList(member.getId()));
            node = controller.findTeamLeadedByUser(leader);
        } catch (GuildSystemException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        Team team = mapper.readValue(node.get("data").toString(), Team.class);
        assert (team.getName().equals("team"));
        assert (team.getLeader().getId().equals(leader.getId()));
        assert (team.getMembers().get(0).getId().equals(member.getId()));
        assert (team.getType().equals(TeamType.ADVENTURE));
    }

}