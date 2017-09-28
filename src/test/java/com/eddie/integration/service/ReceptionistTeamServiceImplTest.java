package com.eddie.integration.service;

import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.AbstractTeamRepository;
import com.eddie.repository.AbstractUserRepository;
import com.eddie.service.TeamService;
import com.eddie.service.impl.ReceptionistTeamServiceImpl;
import com.eddie.unit.fake.repository.FakeAbstractTeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/9/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ReceptionistTeamServiceImplTest {

    private TeamService service;

    @Autowired
    private AbstractTeamRepository mockRepository;

    @Autowired
    private AbstractUserRepository userRepository;

    private Team team;
    private User leader;
    private User member;

    @Before
    public void setUp(){
        service = new ReceptionistTeamServiceImpl(mockRepository);
        team = new Team();
        leader = new User("manager","manager@email","password", Role.MANAGER);
        userRepository.save(leader);
        member = new User("partner","partner@email","password", Role.PARTNER);
        userRepository.save(member);
        team.setName("team");
        team.setLeader(leader);
        team.setMembers(Collections.singletonList(member));
        team.setType(TeamType.RECEPTIONIST);
    }

    @Test
    public void testAdd(){
        service.add(team);
        assert (team.getId() != null);
    }

//    @Test
//    public void testEdit(){
//        mockRepository.save(team);
//        team.setName("editTeam");
//        Team result = service.edit(team);
//        assert (result.getName().equals("editTeam"));
//    }

    @Test
    public void testFindById(){
        mockRepository.save(team);
        Team result = service.findById(team.getId());
        assert (result.equals(team));
    }

    @Test
    public void testFindOneByLeader(){
        mockRepository.save(team);
        Team result = service.findOneByLeader(leader);
        assert (result.getLeader().equals(leader));
    }

    @Test
    public void testFindAllByType(){
        mockRepository.save(team);
        List<Team> result = service.findAllByType(TeamType.RECEPTIONIST);
        for(Team eachTeam : result){
            assert (eachTeam.getType().equals(TeamType.RECEPTIONIST));
        }
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
