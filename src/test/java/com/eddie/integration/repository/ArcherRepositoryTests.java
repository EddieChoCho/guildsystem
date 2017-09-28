package com.eddie.integration.repository;

import com.eddie.model.Archer;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.ArcherRepository;
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
public class ArcherRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArcherRepository archerRepository;

    private User user;

    private Archer archer;

    @Before
    public void setUp(){
        user = new User("name", "fake@mail.com", "12345678", Role.MEMBER);
        entityManager.persist(user);
        archer = new Archer("Emiya", 100,100,user);
        entityManager.persist(archer);
    }

    @Test
    public void testFindAllByUser(){
        List<Archer> result = archerRepository.findAllByUser(user);
        assert (result.get(0).equals(archer));
    }

    @Test
    public void testFindOneByNameAndUser(){
        Archer result = archerRepository.findOneByNameAndUser("Emiya", user);
        assert (result.getUser().equals(user));
        assert (result.getName().equals("Emiya"));
    }
}
