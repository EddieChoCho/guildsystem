package com.eddie.integration.service;

import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.TeamRepository;
import com.eddie.repository.UserRepository;
import com.eddie.service.impl.ReceptionistTeamServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/9/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ReceptionistTeamServiceImplTest {

    private ReceptionistTeamServiceImpl service;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;


    private Team team;
    private User leader;
    private User member;

    @Before
    public void setUp(){
        service = new ReceptionistTeamServiceImpl(teamRepository);
        team = new Team();
        leader = new User("manager","manager@email","password", Role.MANAGER);
        userRepository.save(leader);
        member = new User("partner","partner@email","password", Role.PARTNER);
        userRepository.save(member);
        team.setName("team");
        team.setLeader(leader);
        team.setMembers(Collections.singletonList(member));
        team.setType(TeamType.RECEPTIONIST);
        team = teamRepository.save(team);
    }

    @After
    public void cleanUp(){
        teamRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testAdd_addNewTeamObjectWithNullId_returnTeamObjectWithId(){
        Team team = new Team();
        team.setId(null);
        team.setName("team");
        team.setLeader(leader);
        team.setMembers(Collections.singletonList(member));
        team.setType(TeamType.RECEPTIONIST);
        team = service.add(team);
        Assert.assertTrue(team.getId() != null);
    }

    @Test
    public void testFindById_withIdOfTeam_returnTeam(){
        Team result = service.findById(team.getId());
        Assert.assertEquals(result.getId(),team.getId());
    }

    @Test
    public void testFindById_withIdDoesNotExist_returnNull(){
        Team result = service.findById(99L);
        Assert.assertNull(result);
    }

    @Test
    public void testFindOneByLeader_withLeaderOfATeam_returnTeam(){
        Team result = service.findOneByLeaderId(leader.getId());
        Assert.assertEquals(result.getLeader(),leader);
    }

    @Test
    public void testFindOneByLeader_withUserIsNotALeaderOfAnyTeam_returnNull(){
        Team result = service.findOneByLeaderId(member.getId());
        Assert.assertNull(result);
    }

    @Test
    public void testFindAllByType_withTypeUsedByATeam_returnTeamsFitTheType(){
        List<Team> result = service.findAllByType(TeamType.RECEPTIONIST);
        for(Team eachTeam : result){
            Assert.assertEquals(eachTeam.getType(),TeamType.RECEPTIONIST);
        }
    }

    @Test
    public void testFindAllByType_withTypeDoesNotUsedByAnyTeam_returnEmptyList(){
        List<Team> result = service.findAllByType(TeamType.ADVENTURE);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testCreateATeam(){
        String name =  "team";
        Team team= service.createATeam(name, leader, Collections.singletonList(member));
        assert (team.getName().equals(name));
        assert (team.getLeader().equals(leader));
        assert (team.getMembers().contains(member));
        assert (team.getType().equals(TeamType.RECEPTIONIST));
    }
}
