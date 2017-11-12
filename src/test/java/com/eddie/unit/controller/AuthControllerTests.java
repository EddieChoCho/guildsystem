package com.eddie.unit.controller;

import com.eddie.builder.UserBuilder;
import com.eddie.controller.AuthController;
import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.unit.fake.FakeAuthServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

/**
 * Created by EddieChoCho on 2017/11/13.
 */
public class AuthControllerTests {

    private AuthController controller;

    private FakeAuthServiceImpl mockService;

    private User user;

    @Before
    public void serUp(){
        mockService = new FakeAuthServiceImpl(null);
        controller = new AuthController(mockService, new ObjectMapper());
        user = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
    }

    @Test
    public void testRegister_returnSuccessMessage() throws GuildSystemException {
        JsonNode node = controller.register(user.getRole(), user.getName(), user.getEmail(), user.getPassword(), user.getPassword());
        Assert.assertEquals(node.get("message").textValue(), "success");
    }


    @Test
    public void testLogin_returnSuccessMessage() throws GuildSystemException {
        MockHttpSession mockHttpSession = new MockHttpSession();
        JsonNode node = controller.login(user.getEmail(), user.getPassword(), mockHttpSession);
        Assert.assertEquals (node.get("message").textValue(), "success");
    }

    @Test
    public void testLogout_returnSuccessMessage() throws GuildSystemException {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", user);
        JsonNode node = controller.logout(mockSession);
        Assert.assertEquals (node.get("message").textValue(), "success");
    }

}
