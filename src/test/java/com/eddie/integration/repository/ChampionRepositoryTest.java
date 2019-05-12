package com.eddie.integration.repository;

import com.eddie.model.Archer;
import com.eddie.model.Champion;
import com.eddie.model.Saber;
import com.eddie.model.User;
import com.eddie.repository.UserRepository;
import com.eddie.repository.ChampionRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.eddie.integration.om.ArcherOM.newArcher;
import static com.eddie.integration.om.SaberOM.newSaber;
import static com.eddie.integration.om.UserOM.newUser;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ChampionRepositoryTest {

    @Autowired
    private ChampionRepository unit;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testFindAllByUser_withUserHasASaber_returnSaberInTheList(){
        final User user = newUser("name", "fake@mail.com", "12345678");
        entityManager.persist(user);
        final Saber saber = newSaber("Pendragon", user);
        entityManager.persist(saber);
        final Archer archer = newArcher("Emiya", user);
        entityManager.persist(archer);
        final List<Champion> result = unit.findAllByUser(user);
        final Saber saberFromDb = (Saber)result.get(0);
        final Archer archerFromDb = (Archer)result.get(1);
        Assert.assertEquals(saber, saberFromDb);
        Assert.assertEquals(archer, archerFromDb);
    }

    @After
    public void cleanUp(){
        unit.deleteAll();
        userRepository.deleteAll();
    }


}
