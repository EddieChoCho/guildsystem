package com.eddie.service;

import com.eddie.mock.MockAbstractTeamRepository;
import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.AbstractTeamRepository;
import com.eddie.service.impl.AdventureTeamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/6/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureTeamServiceImplTests {

    private AdventureTeamServiceImpl service;
    private Team team;
    private User leader;
    private User member;

    @Before
    public void setUp(){
        AbstractTeamRepository repository = new MockAbstractTeamRepository();
        service = new AdventureTeamServiceImpl(repository);
        team = new Team();
        leader = new User("leader","leader@email","password", Role.LEADER);
        member = new User("member","member@email","password", Role.MEMBER);
        team.setName("team");
        team.setLeader(leader);
        team.setMembers(Arrays.asList(member));
        team.setType(TeamType.ADVENTURE);
    }

    @Test
    public void testAdd(){
        Team result = service.add(team);
        assert (result.equals(team));
    }

    @Test
    public void testEdit(){
        Team result = service.add(team);
        team.setName("editTeam");
        result = service.edit(team);
        assert (result.getName().equals("editTeam"));
    }

    @Test
    public void testFindById(){
        team = service.add(team);
        Team result = service.findById(team.getId());
        assert (result.equals(team));
    }

    @Test
    public void testDelete(){
        team = service.add(team);
        service.delete(team);
        Team result = service.findById(team.getId());
        assert (result == null);
    }

    @Test
    public void testFindOneByLeader(){
        team = service.add(team);
        Team result = service.findOneByLeader(leader);
        assert (result.equals(team));
    }

    @Test
    public void testFindAllByType(){
        team = service.add(team);
        List<Team> result = service.findAllByType(TeamType.ADVENTURE);
        for(Team eachTeam : result){
            assert (eachTeam.getType().equals(TeamType.ADVENTURE));
        }
    }

    @Test
    public void testCreateATeam(){
        String name =  "team";
        Team team= service.createATeam(name, leader,Arrays.asList(member));
        assert (team.getName().equals(name));
        assert (team.getLeader().equals(leader));
        assert (team.getMembers().equals(Arrays.asList(member)));
        assert (team.getType().equals(TeamType.ADVENTURE));
    }
}

