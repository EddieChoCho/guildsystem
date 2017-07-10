package com.eddie.service;

import com.eddie.builder.UserBuilder;
import com.eddie.mock.FakeUserRepository;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserServiceImplTests {

    private UserService userService;

    private FakeUserRepository mockRepository;

    private User newUser;

    @Before
    public void setUp(){
        mockRepository = new FakeUserRepository();
        userService = new UserServiceImpl(mockRepository);
        newUser = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
    }

    @Test
    public void testAddUser() throws Exception {
        userService.add(newUser);
        assert(mockRepository.userList.contains(newUser));
    }

    @Test
    public void testEditUser() throws Exception {
        mockRepository.save(newUser);
        newUser.setPassword("youShouldNotPass!!!!");
        User  user = userService.edit(newUser);
        assert(user.getPassword().equals("youShouldNotPass!!!!"));
    }

    @Test
    public void testFindById() throws Exception {
        newUser.setId(1L);
        mockRepository.userList.add(newUser);
        User  user = userService.findById(1L);
        assert(user.equals(newUser));
    }

    @Test
    public void testFindAllByIdIn() throws Exception {
        newUser.setId(1L);
        mockRepository.userList.add(newUser);
        List<Long> idList = new ArrayList<>(Collections.singletonList(1L));
        List<User> userList = userService.findAllByIdIn(idList);
        for(User user : userList){
            assert(idList.contains(user.getId()));
        }
    }

    @Test
    public void testFindOneByEmail() throws Exception {
        mockRepository.save(newUser);
        User  user = userService.findOneByEmail("gandalf@mail");
        assert(user.equals(newUser));
    }

    @Test
    public void testDelete() throws Exception {
        mockRepository.save(newUser);
        userService.delete(newUser);
        assert(!mockRepository.userList.contains(newUser));
    }
}
