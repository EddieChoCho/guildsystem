package com.eddie.integration.service;

import com.eddie.builder.UserBuilder;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.AbstractUserRepository;
import com.eddie.repository.UserRepository;
import com.eddie.service.UserService;
import com.eddie.service.impl.UserServiceImpl;
import com.eddie.unit.fake.repository.FakeUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
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
    private AbstractUserRepository mockRepository;

    private User newUser;

    @Before
    public void setUp(){
        userService = new UserServiceImpl(mockRepository);
        newUser = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
    }

    @Test
    public void testAddUser() throws Exception {
        userService.add(newUser);
        assert(newUser.getId() != null);

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
        newUser.setId(2L);
        mockRepository.save(newUser);
        User  user = userService.findById(2L);
        assert(user.getId().equals(2L));
    }

    @Test
    public void testFindAllByIdIn() throws Exception {
        newUser.setId(1L);
        mockRepository.save(newUser);
        List<Long> idList = new ArrayList<>(Collections.singletonList(1L));
        List<User> userList = userService.findAllByIdIn(idList);
        assert (userList.size() == 1);
        assert (userList.get(0).getId().equals(1L));
    }

    @Test
    public void testFindOneByEmail() throws Exception {
        mockRepository.save(newUser);
        User  user = userService.findOneByEmail("gandalf@mail");
        assert(user.getEmail().equals("gandalf@mail"));
        assert(user.equals(newUser));
    }

    @Test
    public void testDelete() throws Exception {
        newUser.setId(2L);
        mockRepository.save(newUser);
        userService.delete(newUser);
        assert(mockRepository.findOne(2L) == null);
    }
}
