package com.eddie.integration.controller;

import com.eddie.controller.AuthController;
import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
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

import static com.eddie.integration.om.UserOM.newUser;

/**
 * Created by EddieChoCho on 2017/11/12.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthControllerTests {
    private AuthController unit;

    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void serUp(){
        authService = new AuthServiceImpl(userRepository);
        unit = new AuthController(authService, new ObjectMapper());
    }
    @After
    public void cleanUp(){
        userRepository.deleteAll();
    }

    @Test
    public void testRegister_withEmailWhichHasNotBeenRegister_returnSuccessMessage() throws GuildSystemException {
        final User user = newUser();
        final JsonNode node = unit.register(user.getRole(), user.getName(), user.getEmail(), user.getPassword(), user.getPassword());
        Assert.assertEquals(node.get("message").textValue(), "success");
    }

    @Test
    public void testRegister_withEmailWhichHasNotBeenRegister_couldStoreUserData() throws GuildSystemException {
        final User user = newUser();
        unit.register(user.getRole(), user.getName(), user.getEmail(), user.getPassword(), user.getPassword());
        final User result =userRepository.findAll().get(0);
        Assert.assertEquals (result.getName(), user.getName());
        Assert.assertEquals (result.getEmail(), user.getEmail());
        Assert.assertEquals (result.getPassword(), user.getPassword());
        Assert.assertEquals (result.getRole(), user.getRole());
    }

    @Test
    public void testLogin_withCorrectEmailAndPassword_returnSuccessMessage() throws GuildSystemException {
        final User user = newUser();
        userRepository.save(user);
        final MockHttpSession mockHttpSession = new MockHttpSession();
        final JsonNode node = unit.login(user.getEmail(), user.getPassword(), mockHttpSession);
        Assert.assertEquals (node.get("message").textValue(), "success");
    }

    @Test
    public void testLogin_withCorrectEmailAndPassword_couldSetSessionAttribute() throws GuildSystemException {
        final User user = newUser();
        userRepository.save(user);
        final MockHttpSession mockHttpSession = new MockHttpSession();
        unit.login(user.getEmail(), user.getPassword(), mockHttpSession);
        final User result = (User) mockHttpSession.getAttribute("user");
        Assert.assertEquals(user.getId(), result.getId());
    }

    @Test
    public void testLogout_withSessionContainsUserAttribute_returnSuccessMessage() throws GuildSystemException {
        final User user = newUser();
        final MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", user);
        final JsonNode node = unit.logout(mockSession);
        Assert.assertEquals (node.get("message").textValue(), "success");
    }

    @Test
    public void testLogout_withSessionContainsUserAttribute_couldRemoveSessionAttribute() throws GuildSystemException {
        final User user = newUser();
        final MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", user);
        unit.logout(mockSession);
        final User result = (User) mockSession.getAttribute("user");
        Assert.assertNull(result);
    }
}
