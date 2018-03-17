package com.eddie.unit;

import com.eddie.builder.UserBuilder;
import com.eddie.controller.AuthController;
import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.service.AuthService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by EddieChoCho on 2017/11/12.
 */
public class AuthControllerTests {

    private AuthController controller;

    private ObjectMapper mapper = new ObjectMapper();

    private User user;

    @Before
    public void setUp() throws GuildSystemException {
        user = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
        AuthService authService= mock(AuthService.class);
        controller = new AuthController(authService, mapper);
    }

    @Test
    public void testRegister_withEmailWhichHasNotBeenRegister_returnSuccessMessage() throws GuildSystemException {
        JsonNode node = controller.register(user.getRole(), user.getName(), user.getEmail(), user.getPassword(), user.getPassword());
        Assert.assertEquals(node.get("message").textValue(), "success");
    }

    @Test
    public void testLogin_withCorrectEmailAndPassword_returnSuccessMessage() throws GuildSystemException {
        MockHttpSession mockHttpSession = new MockHttpSession();
        JsonNode node = controller.login(user.getEmail(), user.getPassword(), mockHttpSession);
        Assert.assertEquals (node.get("message").textValue(), "success");
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

        AuthService authService= mock(AuthService.class);
        when(authService.removeUserFromSession(isA(HttpSession.class))).thenAnswer(i ->{
            mockSession.removeAttribute("user");
            return mockSession;
        });

        controller = new AuthController(authService, mapper);
        controller.logout(mockSession);
        User result = (User) mockSession.getAttribute("user");
        Assert.assertNull(result);
    }
}
