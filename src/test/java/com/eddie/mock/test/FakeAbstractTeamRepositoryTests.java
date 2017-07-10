package com.eddie.mock.test;

import com.eddie.builder.UserBuilder;
import com.eddie.mock.FakeAbstractTeamRepository;
import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/6/28.
 */
public class FakeAbstractTeamRepositoryTests {

    private FakeAbstractTeamRepository repository;

    private User leader;

    private Team team;

    @Before
    public void setUp(){
        repository = new FakeAbstractTeamRepository();
        leader = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
        User member = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
        team = new Team("team", TeamType.ADVENTURE,leader, Collections.singletonList(member));
    }

    @Test
    public void testSave(){
        Team result = repository.save(team);
        assert (result.equals(team));
    }

    @Test
    public void testFindOne(){
        team.setId(1L);
        repository.teamList.add(team);
        Team result = repository.findOne(1L);
        assert (result.equals(team));
    }

    @Test
    public void testFindOneByLeader(){
        repository.teamList.add(team);
        Team result = repository.findOneByLeader(leader);
        assert (result.equals(team));
    }

    @Test
    public void testFindAllByType(){
        repository.teamList.add(team);
        List<Team> teams = repository.findAllByType(TeamType.ADVENTURE);
        for(Team eachTeam : teams){
            assert (eachTeam.getType().equals(TeamType.ADVENTURE));
        }
    }

    @Test
    public void testDelete(){
        repository.teamList.add(team);
        repository.delete(team);
        Team team = repository.findOneByLeader(leader);
        assert (!repository.teamList.contains(team));
    }
}

