package com.eddie;

import com.eddie.builder.UserBuilder;
import com.eddie.mock.MockUserRepository;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.service.UserService;
import com.eddie.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserServiceTests {

    private UserService userService;

    private MockUserRepository repository;

    private UserBuilder builder;

    public UserServiceTests(){
        repository = new MockUserRepository();
        userService = new UserServiceImpl(repository);
        builder = new UserBuilder();
    }

    @Test
    public void testAddUser() throws Exception {
        User newUser = builder.setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
        User  user = userService.add(newUser);
        assert(user.equals(newUser));
    }

    @Test
    public void testEditUser() throws Exception {
        User newUser = builder.setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
        userService.add(newUser);
        newUser.setPassword("youShouldNotPass!!!!");
        User  user = userService.edit(newUser);
        assert(user.equals(newUser));
    }

    @Test
    public void testFindById() throws Exception {
        User newUser = builder.setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
        newUser = userService.add(newUser);
        User  user = userService.findById(newUser.getId());
        assert(user.equals(newUser));
    }

    @Test
    public void testFindOneByEmail() throws Exception {
        User newUser = builder.setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
        userService.add(newUser);
        User  user = userService.findOneByEmail("gandalf@mail");
        assert(user.equals(newUser));
    }

    @Test
    public void testDelete() throws Exception {
        User newUser = builder.setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
        userService.add(newUser);
        userService.delete(newUser);
        User  user = userService.findById(newUser.getId());
        assert(user == null);
    }
}
