package com.eddie.unit.controller;

import com.eddie.builder.UserBuilder;
import com.eddie.controller.AuthController;
import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.UserRepository;
import com.eddie.service.AuthService;
import com.eddie.service.impl.AuthServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by EddieChoCho on 2017/11/12.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthControllerTests {
    private AuthController controller;

    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void serUp(){
        authService = new AuthServiceImpl(userRepository);
        controller = new AuthController(authService, new ObjectMapper());
        user = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
    }
    @After
    public void cleanUp(){
        userRepository.deleteAll();
    }

    @Test
    public void testRegister_withEmailWhichHasNotBeenRegister_returnSuccessMessage() throws GuildSystemException {
        JsonNode node = controller.register(user.getRole(), user.getName(), user.getEmail(), user.getPassword(), user.getPassword());
        Assert.assertEquals(node.get("message").textValue(), "success");
    }

    @Test
    public void testRegister_withEmailWhichHasNotBeenRegister_couldStoreUserData() throws GuildSystemException {
        controller.register(user.getRole(), user.getName(), user.getEmail(), user.getPassword(), user.getPassword());
        User result =userRepository.findAll().get(0);
        Assert.assertEquals (result.getName(), user.getName());
        Assert.assertEquals (result.getEmail(), user.getEmail());
        Assert.assertEquals (result.getPassword(), user.getPassword());
        Assert.assertEquals (result.getRole(), user.getRole());
    }

    @Test
    public void testLogin_withCorrectEmailAndPassword_returnSuccessMessage() throws GuildSystemException {
        userRepository.save(user);
        MockHttpSession mockHttpSession = new MockHttpSession();
        JsonNode node = controller.login(user.getEmail(), user.getPassword(), mockHttpSession);
        Assert.assertEquals (node.get("message").textValue(), "success");
    }

    @Test
    public void testLogin_withCorrectEmailAndPassword_couldSetSessionAttribute() throws GuildSystemException {
        userRepository.save(user);
        MockHttpSession mockHttpSession = new MockHttpSession();
        controller.login(user.getEmail(), user.getPassword(), mockHttpSession);
        User result = (User) mockHttpSession.getAttribute("user");
        Assert.assertEquals(user.getId(), result.getId());
    }

    @Test
    public void testLogout_withSessionContainsUserAttribute_returnSuccessMessage() throws GuildSystemException {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", user);
        JsonNode node = controller.logout(mockSession);
        Assert.assertEquals (node.get("message").textValue(), "success");
    }

    @Test
    public void testLogout_withSessionContainsUserAttribute_couldRemoveSessionAttribute() throws GuildSystemException {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", user);
        controller.logout(mockSession);
        User result = (User) mockSession.getAttribute("user");
        Assert.assertNull(result);
    }
}
