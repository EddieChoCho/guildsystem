package com.eddie.controller;

import com.eddie.builder.UserBuilder;
import com.eddie.exception.GuildSystemException;
import com.eddie.mock.FakeUserRepository;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.service.impl.AuthServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

/**
 * Created by EddieChoCho on 2017/7/10.
 */
public class AuthControllerTests {
    private AuthController controller;
    private FakeUserRepository mockRepository;
    private User user;

    @Before
    public void serUp(){
        mockRepository = new FakeUserRepository();
        controller = new AuthController(new AuthServiceImpl(mockRepository), new ObjectMapper());
        user = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
    }
    @Test
    public void testRegister() throws GuildSystemException {
        JsonNode node = controller.register(user.getRole(), user.getName(), user.getEmail(), user.getPassword(), user.getPassword());
        assert (node.get("message").textValue().equals("success"));
    }

    @Test
    public void testRegisterCouldStoreUserData() throws GuildSystemException {
        controller.register(user.getRole(), user.getName(), user.getEmail(), user.getPassword(), user.getPassword());
        User result =mockRepository.userList.get(0);
        assert (result.getName().equals(user.getName()));
        assert (result.getEmail().equals(user.getEmail()));
        assert (result.getPassword().equals(user.getPassword()));
        assert (result.getRole().equals(user.getRole()));
    }

    @Test
    public void testLogin() throws GuildSystemException {
        mockRepository.save(user);
        MockHttpSession mockHttpSession = new MockHttpSession();
        JsonNode node = controller.login(user.getEmail(), user.getPassword(), mockHttpSession);
        assert (node.get("message").textValue().equals("success"));
    }

    @Test
    public void testLoginCouldSetSessionAttribute() throws GuildSystemException {
        mockRepository.save(user);
        MockHttpSession mockHttpSession = new MockHttpSession();
        controller.login(user.getEmail(), user.getPassword(), mockHttpSession);
        User result = (User) mockHttpSession.getAttribute("user");
        assert(user.getId().equals(result.getId()));
    }

    @Test
    public void testLogout() throws GuildSystemException {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", user);
        JsonNode node = controller.logout(mockSession);
        assert (node.get("message").textValue().equals("success"));
    }

    @Test
    public void testLogoutCouldRemoveSessionAttribute() throws GuildSystemException {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("user", user);
        controller.logout(mockSession);
        User result = (User) mockSession.getAttribute("user");
        assert(result == null);
    }
}
