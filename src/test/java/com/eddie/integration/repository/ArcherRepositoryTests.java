package com.eddie.integration.repository;

import com.eddie.model.Archer;
import com.eddie.model.Saber;
import com.eddie.model.User;
import com.eddie.repository.ArcherRepository;
import com.eddie.repository.SaberRepository;
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

import static com.eddie.integration.om.ArcherOM.newArcher;
import static com.eddie.integration.om.SaberOM.newSaber;
import static com.eddie.integration.om.UserOM.newUser;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ArcherRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArcherRepository unit;

    @Autowired
    private SaberRepository saberRepository;

    @Autowired
    private UserRepository userRepository;

    @After
    public void cleanUp(){
        unit.deleteAll();
        saberRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    public void testFindAllByUser_withUserHasAnArcher_returnArcherInTheList(){
        final User userHasAnArcher = newUser("name", "fake@mail.com", "12345678");
        entityManager.persist(userHasAnArcher);
        final Archer archer = newArcher("Emiya", userHasAnArcher);
        entityManager.persist(archer);
        final List<Archer> result = unit.findAllByUser(userHasAnArcher);
        Assert.assertEquals(archer, result.get(0));
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testFindAllByUser_withUserHasAnSaber_returnEmptyList(){
        final User userHasAnSaber = newUser("name", "fake@mail.com", "12345678");
        entityManager.persist(userHasAnSaber);
        final Saber saber = newSaber("Pendragon", userHasAnSaber);
        entityManager.persist(saber);
        final List<Archer> result = unit.findAllByUser(userHasAnSaber);
        Assert.assertTrue (result.isEmpty());
    }

    @Test
    public void testFindAllByUser_withUserHasAnArcher_returnEmptyList(){
        final User userHasNoArcher = newUser("userHasNoArcher", "userHasNoArcher@mail.com", "12345678");
        entityManager.persist(userHasNoArcher);
        final List<Archer> result = unit.findAllByUser(userHasNoArcher);
        Assert.assertTrue (result.isEmpty());
    }

    @Test
    public void testFindOneByNameAndUser_withRightNameAndUser_returnArcherInTheList(){
        final User userHasAnArcher = newUser("name", "fake@mail.com", "12345678");
        entityManager.persist(userHasAnArcher);
        final String nameOfArcher = "Emiya";
        final Archer archer = newArcher(nameOfArcher, userHasAnArcher);
        entityManager.persist(archer);
        final Archer result = unit.findOneByNameAndUser(nameOfArcher, userHasAnArcher);
        Assert.assertTrue (result.getUser().equals(userHasAnArcher));
        Assert.assertTrue (result.getName().equals(nameOfArcher));
    }

    @Test
    public void testFindOneByNameAndUser_withRightNameButWrongUser_returnArcherInTheList(){
        final User userHasNoArcher = newUser("userHasNoArcher", "userHasNoArcher@mail.com", "12345678");
        entityManager.persist(userHasNoArcher);
        final User userHasAnArcher = newUser("name", "fake@mail.com", "12345678");
        entityManager.persist(userHasAnArcher);
        final String nameOfArcher = "Emiya";
        final Archer archer = newArcher(nameOfArcher, userHasAnArcher);
        entityManager.persist(archer);
        final Archer result = unit.findOneByNameAndUser(nameOfArcher, userHasNoArcher);
        Assert.assertNull(result);
    }

    @Test
    public void testFindOneByNameAndUser_withRightUserButWrongName_returnArcherInTheList(){
        final User userHasAnArcher = newUser("name", "fake@mail.com", "12345678");
        entityManager.persist(userHasAnArcher);
        final String nameOfArcher = "Emiya";
        final Archer archer = newArcher(nameOfArcher, userHasAnArcher);
        entityManager.persist(archer);
        final String wrongName = "Gilgamesh";
        final Archer result = unit.findOneByNameAndUser(wrongName, userHasAnArcher);
        Assert.assertNull(result);
    }
}
