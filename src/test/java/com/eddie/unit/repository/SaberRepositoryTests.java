package com.eddie.unit.repository;

import com.eddie.model.Saber;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.SaberRepository;
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
public class SaberRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SaberRepository saberRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    private User userHasNoSaber;

    private Saber saber;

    @Before
    public void setUp(){
        user = new User("name", "fake@mail.com", "12345678", Role.MEMBER);
        entityManager.persist(user);
        userHasNoSaber = new User("userHasNoSaber", "userHasNoSaber@mail.com", "12345678", Role.MEMBER);
        entityManager.persist(userHasNoSaber);
        saber = new Saber("Pendragon", 100,100,user);
        entityManager.persist(saber);
    }

    @After
    public void cleanUp(){
        saberRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testFindAllByUser_withUserHasASaber_returnSaberInTheList(){
        List<Saber> result = saberRepository.findAllByUser(user);
        Assert.assertTrue (result.get(0).equals(saber));
    }

    @Test
    public void testFindAllByUser_withUserHasNoSaber_returnEmptyList(){
        List<Saber> result = saberRepository.findAllByUser(userHasNoSaber);
        Assert.assertTrue  (result.isEmpty());
    }

    @Test
    public void testFindOneByNameAndUser_withRightNameAndUser_returnSaber(){
        Saber result = saberRepository.findOneByNameAndUser("Pendragon", user);
        Assert.assertTrue (result.getUser().equals(user));
        Assert.assertTrue (result.getName().equals("Pendragon"));
    }

    @Test
    public void testFindOneByNameAndUser_withRightNameButWrongUser_returnNull(){
        Saber result = saberRepository.findOneByNameAndUser("Pendragon", userHasNoSaber);
        Assert.assertNull(result);
    }

    @Test
    public void testFindOneByNameAndUser_withRightUserButWrongName_returnNull(){
        Saber result = saberRepository.findOneByNameAndUser("Attila", user);
        Assert.assertNull(result);
    }



}

