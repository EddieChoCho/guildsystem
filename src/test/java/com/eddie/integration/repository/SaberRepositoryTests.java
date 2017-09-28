package com.eddie.integration.repository;

import com.eddie.model.Saber;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.SaberRepository;
import com.eddie.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class SaberRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SaberRepository saberRepository;

    private User user;

    private Saber saber;

    @Before
    public void setUp(){
        user = new User("name", "fake@mail.com", "12345678", Role.MEMBER);
        entityManager.persist(user);
        saber = new Saber("Pendragon", 100,100,user);
        entityManager.persist(saber);
    }

    @Test
    public void testFindAllByUser(){
        List<Saber> result = saberRepository.findAllByUser(user);
        assert (result.get(0).equals(saber));
    }

    @Test
    public void testFindOneByNameAndUser(){
        Saber result = saberRepository.findOneByNameAndUser("Pendragon", user);
        assert (result.getUser().equals(user));
        assert (result.getName().equals("Pendragon"));
    }



}

