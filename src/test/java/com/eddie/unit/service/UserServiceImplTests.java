package com.eddie.unit.service;

import com.eddie.builder.UserBuilder;
import com.eddie.exception.UserException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.UserRepository;
import com.eddie.service.UserService;
import com.eddie.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/9/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceImplTests {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User newUser;

    @Before
    public void setUp(){
        userService = new UserServiceImpl(userRepository);
        newUser = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
    }

    @After
    public void cleanUp(){
        userRepository.deleteAll();
    }

    @Test
    public void testAddUser_() throws Exception {
        User result = userService.add(newUser);
        Assert.assertTrue(result.getId() != null);
    }

    @Test(expected = UserException.class)
    public void testAddUser_withEmailHasBeanUsed_throwException() throws Exception {
        String email = newUser.getEmail();
        newUser = userService.add(newUser);
        User userWithEmailHasBeenUsed = new UserBuilder().setName("NotGandalf").setEmail(email).setPassword("youShoudNotPass!!!").setRole(Role.LEADER).build();
        userService.add(userWithEmailHasBeenUsed);
    }

    @Test
    public void testEditUser() throws Exception {
        userRepository.save(newUser);
        newUser.setPassword("youShouldPass!!!!");
        User  user = userService.edit(newUser);
        Assert.assertTrue(user.getPassword().equals("youShouldPass!!!!"));
    }

    @Test
    public void testFindById() throws Exception {
        Long id = userRepository.save(newUser).getId();
        User  user = userService.findById(id);
        Assert.assertTrue(user.getId().equals(id));
    }

    @Test
    public void testFindAllByIdIn() throws Exception {
        Long id = userRepository.save(newUser).getId();
        List<Long> idList = new ArrayList<>(Collections.singletonList(id));
        List<User> userList = userService.findAllByIdIn(idList);
        Assert.assertTrue(userList.size() == 1);
        Assert.assertTrue(userList.stream().anyMatch(user -> user.getId().equals(id)));
    }

    @Test
    public void testFindOneByEmail() throws Exception {
        userRepository.save(newUser);
        User  user = userService.findOneByEmail("gandalf@mail");
        assert(user.getEmail().equals("gandalf@mail"));
        assert(user.equals(newUser));
    }

    @Test
    public void testDelete() throws Exception {
        userRepository.save(newUser);
        userService.delete(newUser);
        Assert.assertTrue(userRepository.findAll().isEmpty());
    }
}
