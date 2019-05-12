package com.eddie.integration.repository;

import com.eddie.model.ExperimentalTeam;
import com.eddie.model.User;
import com.eddie.model.enums.TeamType;
import com.eddie.repository.ExperimentalTeamRepository;
import com.eddie.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.eddie.integration.om.ExperimentalTeamOM.newExperimentalTeam;
import static com.eddie.integration.om.UserOM.newUser;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExperimentalTeamRepositoryTest {

    @Autowired
    private ExperimentalTeamRepository unit;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAllByAdvisorId(){
        final User advisor = newUser("advisor", "advise@com", "password");
        entityManager.persist(advisor);
        final ExperimentalTeam team = newExperimentalTeam("AceTeam", TeamType.ADVENTURE, advisor);
        entityManager.persist(team);
        final List<ExperimentalTeam> result = unit.findAllByAdvisorId(advisor.getId());
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(team, result.get(0));
    }

    @After
    public void cleanUp(){
        unit.deleteAll();
        userRepository.deleteAll();
    }
}
