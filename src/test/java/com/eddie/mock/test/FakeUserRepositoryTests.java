package com.eddie.mock.test;

import com.eddie.builder.UserBuilder;
import com.eddie.mock.FakeUserRepository;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/6/28.
 */
public class FakeUserRepositoryTests {

    private FakeUserRepository repository;

    private User newUser;

    @Before
    public void setUp(){
        repository = new FakeUserRepository();
        newUser = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
    }

    @Test
    public void testSave(){
        User user = repository.save(newUser);
        assert (user.equals(newUser));
    }

    @Test
    public void testFindOne(){
        newUser.setId(1L);
        repository.userList.add(newUser);
        User user = repository.findOne(1L);
        assert (user.equals(newUser));
    }

    @Test
    public void testFindAllByIdIn(){
        newUser.setId(1L);
        repository.userList.addAll(Collections.singletonList(newUser));
        List<Long> idList = new ArrayList<>(Collections.singletonList(1L));
        List<User> users = repository.findAllByIdIn(idList);
        assert (users.size() == idList.size());
    }

    @Test
    public void testFindOneByEmail(){
        repository.userList.add(newUser);
        User user = repository.findOneByEmail(newUser.getEmail());
        assert (user.equals(newUser));
    }

    @Test
    public void testFindOneByEmailAndPassword(){
        repository.userList.add(newUser);
        User user = repository.findOneByEmailAndPassword(newUser.getEmail(), newUser.getPassword());
        assert (user.equals(newUser));
    }

    @Test
    public void testDelete(){
        repository.userList.add(newUser);
        repository.delete(newUser);
        assert (!repository.userList.contains(newUser));
    }
}
