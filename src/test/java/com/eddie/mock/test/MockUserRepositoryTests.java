package com.eddie.mock.test;

import com.eddie.builder.UserBuilder;
import com.eddie.mock.MockUserRepository;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/6/28.
 */
public class MockUserRepositoryTests {

    private MockUserRepository repository;

    private User newUser;

    @Before
    public void setUp(){
        repository = new MockUserRepository();
        newUser = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
    }

    @Test
    public void testSave(){
        User user = repository.save(newUser);
        assert (user.equals(newUser));
    }

    @Test
    public void testFindOne(){
        repository.save(newUser);
        User user = repository.findOne(1L);
        assert (user.equals(newUser));
    }

    @Test
    public void testFindAllByIdIn(){
        repository.save(newUser);
        List<Long> idList = new ArrayList<>(Arrays.asList(1L));
        List<User> users = repository.findAllByIdIn(idList);
        assert (users.size() == idList.size());
    }

    @Test
    public void testFindOneByEmail(){
        repository.save(newUser);
        User user = repository.findOneByEmail(newUser.getEmail());
        assert (user.equals(newUser));
    }

    @Test
    public void testFindOneByEmailAndPassword(){
        repository.save(newUser);
        User user = repository.findOneByEmailAndPassword(newUser.getEmail(), newUser.getPassword());
        assert (user.equals(newUser));
    }

    @Test
    public void testDelete(){
        repository.save(newUser);
        repository.delete(newUser);
        User user = repository.findOneByEmail(newUser.getEmail());
        assert (user == null);
    }
}