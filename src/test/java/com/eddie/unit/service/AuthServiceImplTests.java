package com.eddie.unit.service;

import com.eddie.builder.UserBuilder;
import com.eddie.exception.AuthException;
import com.eddie.exception.GuildSystemException;
import com.eddie.unit.mock.FakeUserRepository;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.service.impl.AuthServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

/**
 * Created by EddieChoCho on 2017/7/6.
 */
public class AuthServiceImplTests {

    private AuthServiceImpl authService;
    private FakeUserRepository mockRepository;
    private MockHttpSession mockSession;
    private User user;

    @Before
    public void setUp(){
        mockRepository = new FakeUserRepository();
        authService = new AuthServiceImpl(mockRepository);
        mockSession = new MockHttpSession();
        user = new UserBuilder().setName("Gandalf").setEmail("gandalf@mail").setPassword("weShouldCallSomeEagles").setRole(Role.LEADER).build();
    }

    @Test
    public void testRegister() throws GuildSystemException {
        User result = authService.register(user.getName(), user.getEmail(), user.getPassword(), user.getPassword(), user.getRole());
        assert (result.getName().equals(user.getName()));
        assert (result.getEmail().equals(user.getEmail()));
        assert (result.getPassword().equals(user.getPassword()));
        assert (result.getRole().equals(user.getRole()));
    }

    @Test(expected = AuthException.class)
    public void testRegisterWithWrongPasswordConfirm() throws GuildSystemException {
        authService.register(user.getName(), user.getEmail(), user.getPassword(), "someThingElse", user.getRole());
    }

    @Test(expected = AuthException.class)
    public void testRegisterWithEmailWitchHasBeenRegistered() throws GuildSystemException {
        mockRepository.save(user);
        authService.register(user.getName(), user.getEmail(), user.getPassword(), user.getPassword(), user.getRole());
    }

    @Test
    public void testLogin() throws GuildSystemException {
        mockRepository.save(user);
        HttpSession session = authService.login(mockSession, user.getEmail(), user.getPassword());
        User result = (User) session.getAttribute("user");
        assert(user.getId().equals(result.getId()));
    }

    @Test(expected = AuthException.class)
    public void testLoginWithEmailWitchHasNotRegisteredYet() throws GuildSystemException {
        mockRepository.save(user);
        authService.login(mockSession, "EmailHasNotRegisteredYet", user.getPassword());
    }

    @Test(expected = AuthException.class)
    public void testLoginWithWrongPassword() throws GuildSystemException {
        mockRepository.save(user);
        authService.login(mockSession, user.getEmail(), "WrongPassword");
    }

    @Test
    public void testLogout() throws GuildSystemException {
        mockSession.setAttribute("user", user);
        authService.logout(mockSession);
        User result = (User) mockSession.getAttribute("user");
        assert(result == null);
    }
}
