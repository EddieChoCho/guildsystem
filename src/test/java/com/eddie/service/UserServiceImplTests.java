package com.eddie.service;

import com.eddie.builder.UserBuilder;
import com.eddie.mock.MockUserRepository;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.service.UserService;
import com.eddie.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTests {

    private UserService userService;

    private User newUser;

    @Before
    public void setUp(){
        MockUserRepository repository = new MockUserRepository();
        userService = new UserServiceImpl(repository);
        newUser = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
    }

    @Test
    public void testAddUser() throws Exception {
        User  user = userService.add(newUser);
        assert(user.equals(newUser));
    }

    @Test
    public void testEditUser() throws Exception {
        userService.add(newUser);
        newUser.setPassword("youShouldNotPass!!!!");
        User  user = userService.edit(newUser);
        assert(user.equals(newUser));
    }

    @Test
    public void testFindById() throws Exception {
        newUser = userService.add(newUser);
        User  user = userService.findById(newUser.getId());
        assert(user.equals(newUser));
    }

    @Test
    public void testFindOneByEmail() throws Exception {
        userService.add(newUser);
        User  user = userService.findOneByEmail("gandalf@mail");
        assert(user.equals(newUser));
    }

    @Test
    public void testDelete() throws Exception {
        userService.add(newUser);
        userService.delete(newUser);
        User  user = userService.findById(newUser.getId());
        assert(user == null);
    }
}
