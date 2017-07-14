package com.eddie.collaboration.controller;

import com.eddie.builder.PlayerBuilder;
import com.eddie.controller.AdventureTeamController;
import com.eddie.exception.GuildSystemException;
import com.eddie.unit.fake.repository.FakeAbstractTeamRepository;
import com.eddie.unit.fake.repository.FakeUserRepository;
import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import com.eddie.model.pojo.Leader;
import com.eddie.repository.AbstractUserRepository;
import com.eddie.response.impl.DataResponse;
import com.eddie.service.impl.AdventureTeamServiceImpl;
import com.eddie.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

/**
 * Created by EddieChoCho on 2017/6/27.
 */
public class AdventureTeamControllerCollaborationTests {

    private AdventureTeamController controller;
    private FakeAbstractTeamRepository mockRepository;
    private Leader leader;
    private User member;

    @Before
    public void setUp() throws GuildSystemException {
        AbstractUserRepository userRepository = new FakeUserRepository();
        mockRepository = new FakeAbstractTeamRepository();
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        ObjectMapper mapper = new ObjectMapper();
        DataResponse<Team> response = new DataResponse<>(mapper);
        AdventureTeamServiceImpl teamService = new AdventureTeamServiceImpl(mockRepository);
        controller = new AdventureTeamController(teamService, userService, response);
        User user = new User("leader", "leader@email", "password", Role.LEADER);
        leader = new PlayerBuilder(user).buildLeader();
        member = new User("member", "member@email", "password", Role.MEMBER);
        userService.add(leader);
        userService.add(member);
    }

    @Test
    public void testCreateTeam() throws GuildSystemException {
        JsonNode node = controller.createTeam(leader, "team", Collections.singletonList(member.getId()));
        assert (node.get("msg").textValue().equals("success"));
    }

    @Test
    public void testCreateTeamCouldStoreTeamData() throws GuildSystemException {
        controller.createTeam(leader, "team", Collections.singletonList(member.getId()));
        Team teem =mockRepository.teamList.get(0);
        assert (teem.getLeader().equals(leader));
        assert (teem.getName().equals("team"));
        assert (teem.getMembers().contains(member));
        assert (teem.getType().equals(TeamType.ADVENTURE));
    }

    @Test
    public void testFindTeamLeadedByUser() throws IOException, GuildSystemException {
        Team newTeam = new Team("team",TeamType.ADVENTURE,leader, Collections.singletonList(member));
        mockRepository.teamList.add(newTeam);
        JsonNode node = controller.findTeamLeadedByUser(leader);
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