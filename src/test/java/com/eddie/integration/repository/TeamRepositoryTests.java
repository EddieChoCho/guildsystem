package com.eddie.integration.repository;

import com.eddie.model.Team;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.TeamRepository;
import com.eddie.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/9/10.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    private User leader;

    private Long leaderId;

    private User member1;

    private User member2;

    @Before
    public void setUp(){
        leader = new User("leader", "leader@mail.com", "12345678", Role.LEADER);
        entityManager.persist(leader);
        leaderId = leader.getId();
        member1 = new User("member1", "member1@mail.com", "12345678", Role.MEMBER);
        entityManager.persist(member1);
        member2 = new User("member2", "member2@mail.com", "12345678", Role.MEMBER);
        entityManager.persist(member2);
        Team team = new Team("Avenger", TeamType.ADVENTURE, leader, Arrays.asList(member1,member2));
        entityManager.persist(team);
    }

    @After
    public void cleanUp(){
        teamRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testFindAllByType_withTeamTypeADVENTURE_returnAllTeamWhichFitTheType(){
        List<Team> result = teamRepository.findAllByType(TeamType.ADVENTURE);
        assert (result != null);
        for(Team team : result){
            assert (team.getType().equals(TeamType.ADVENTURE));
        }
    }

    @Test
    public void testFindAllByType_withTeamTypeWhichIsNotUsedByAnyTeam_returnEmptyList(){
        List<Team> result = teamRepository.findAllByType(TeamType.RECEPTIONIST);
        assert (result != null);
        assert (result.size() == 0);
    }

    @Test
    public void testFindOneByLeaderId_withIdBelongsToUserWhoIsTheLeaderOfTheTeam_returnTeam(){
        Team result = teamRepository.findOneByLeaderId(leader.getId());
        assert (result != null);
        assert (result.getLeader().equals(leader));
    }

    @Test
    public void testFindOneByLeaderId_withIdBelongsToUserWhoIsNotTheLeaderOfTheTeam_returnNull(){
        Team result = teamRepository.findOneByLeaderId(member1.getId());
        assert (result == null);
    }

    @Test
    public void testFindOneByLeaderId_withIdDoesNotBelongsToAnyUser_returnNull(){
        Team result = teamRepository.findOneByLeaderId(4L);
        assert (result == null);
    }
}
