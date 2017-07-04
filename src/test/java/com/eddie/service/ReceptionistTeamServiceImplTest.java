package com.eddie.service;

import com.eddie.mock.FakeAbstractTeamRepository;
import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.AbstractTeamRepository;
import com.eddie.service.impl.ReceptionistTeamServiceImpl;
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
public class ReceptionistTeamServiceImplTest {

    private ReceptionistTeamServiceImpl service;
    private FakeAbstractTeamRepository mockRepository;
    private Team team;
    private User leader;
    private User member;

    @Before
    public void setUp(){
        mockRepository = new FakeAbstractTeamRepository();
        service = new ReceptionistTeamServiceImpl(mockRepository);
        team = new Team();
        leader = new User("manager","manager@email","password", Role.MANAGER);
        member = new User("partner","partner@email","password", Role.PARTNER);
        team.setName("team");
        team.setLeader(leader);
        team.setMembers(Arrays.asList(member));
        team.setType(TeamType.RECEPTIONIST);
    }

    @Test
    public void testAdd(){
        Team result = service.add(team);
        assert (result.equals(team));
    }

    @Test
    public void testEdit(){
        mockRepository.save(team);
        team.setName("editTeam");
        Team result = service.edit(team);
        assert (result.getName().equals("editTeam"));
    }

    @Test
    public void testFindById(){
        mockRepository.save(team);
        Team result = service.findById(team.getId());
        assert (result.equals(team));
    }

    @Test
    public void testDelete(){
        mockRepository.save(team);
        service.delete(team);
        assert (!mockRepository.teamList.contains(team));
    }

    @Test
    public void testFindOneByLeader(){
        mockRepository.save(team);
        Team result = service.findOneByLeader(leader);
        assert (result.equals(team));
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
        Team team= service.createATeam(name, leader,Arrays.asList(member));
        assert (team.getName().equals(name));
        assert (team.getLeader().equals(leader));
        assert (team.getMembers().equals(Arrays.asList(member)));
        assert (team.getType().equals(TeamType.RECEPTIONIST));
    }
}
