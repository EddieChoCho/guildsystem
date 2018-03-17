package com.eddie.integration.repository;

import com.eddie.model.Archer;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.ArcherRepository;
import com.eddie.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ArcherRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArcherRepository archerRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    private User userHasNoArcher;

    private Archer archer;

    @Before
    public void setUp(){
        user = new User("name", "fake@mail.com", "12345678", Role.MEMBER);
        entityManager.persist(user);
        userHasNoArcher = new User("userHasNoArcher", "userHasNoArcher@mail.com", "12345678", Role.MEMBER);
        entityManager.persist(userHasNoArcher);
        archer = new Archer("Emiya", 100,100,user);
        entityManager.persist(archer);
    }

    @After
    public void cleanUp(){
        archerRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    public void testFindAllByUser_withUserHasAnArcher_returnArcherInTheList(){
        List<Archer> result = archerRepository.findAllByUser(user);
        Assert.assertTrue(result.get(0).equals(archer));
    }

    @Test
    public void testFindAllByUser_withUserHasAnArcher_returnEmptyList(){
        List<Archer> result = archerRepository.findAllByUser(userHasNoArcher);
        Assert.assertTrue (result.isEmpty());
    }

    @Test
    public void testFindOneByNameAndUser_withRightNameAndUser_returnArcherInTheList(){
        Archer result = archerRepository.findOneByNameAndUser("Emiya", user);
        Assert.assertTrue (result.getUser().equals(user));
        Assert.assertTrue (result.getName().equals("Emiya"));
    }

    @Test
    public void testFindOneByNameAndUser_withRightNameButWrongUser_returnArcherInTheList(){
        Archer result = archerRepository.findOneByNameAndUser("Emiya", userHasNoArcher);
        Assert.assertNull(result);
    }

    @Test
    public void testFindOneByNameAndUser_withRightUserButWrongName_returnArcherInTheList(){
        Archer result = archerRepository.findOneByNameAndUser("Gilgamesh", user);
        Assert.assertNull(result);
    }
}
