package com.eddie.unit.repository;

import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by EddieChoCho on 2017/9/10.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp(){
        User user = new User("name", "fake@mail.com", "12345678", Role.MEMBER);
        entityManager.persist(user);
    }
    @Test
    public void testFindOneByEmail(){
        User result = userRepository.findOneByEmail("fake@mail.com");
        assert (result != null);
        assert (result.getEmail().equals("fake@mail.com"));
    }

    @Test
    public void testFindOneByEmail_withWrongEmail(){
        User result = userRepository.findOneByEmail("wrong@mail.com");
        assert (result == null);
    }

    @Test
    public void testFindOneByEmailAndPassword(){
        User result = userRepository.findOneByEmailAndPassword("fake@mail.com","12345678");
        assert (result != null);
        assert (result.getEmail().equals("fake@mail.com"));
    }

    @Test
    public void testFindOneByEmailAndPassword_withWrongEmail(){
        User result = userRepository.findOneByEmailAndPassword("wrong@mail.com","12345678");
        assert (result == null);
    }

    @Test
    public void testFindOneByEmailAndPassword_withWrongPassword(){
        User result = userRepository.findOneByEmailAndPassword("fake@mail.com","87654321");
        assert (result == null);
    }

    @Test
    public void testFindOneByEmailAndPassword_withWrongEmailAndWrongPassword(){
        User result = userRepository.findOneByEmailAndPassword("wrong@mail.com","87654321");
        assert (result == null);
    }

}
