package com.eddie.unit.fake.service;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import com.eddie.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EddieChoCho on 2017/7/14.
 */
public class FakeUserService implements UserService {

    @Override
    public User add(User user) {
        return user;
    }

    @Override
    public User edit(User user) {
        return user;
    }

    @Override
    public User findById(Long aLong) {
        User user = new User();
        user.setId(aLong);
        return user;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User findOneByEmail(String email) throws GuildSystemException {
        User user = new User();
        user.setEmail(email);
        return user;
    }

    @Override
    public List<User> findAllByIdIn(List<Long> idList) {
        List<User> userList = new ArrayList<>();
        for(Long id : idList){
            User user = new User();
            user.setId(id);
            userList.add(user);
        }
        return userList;
    }
}
