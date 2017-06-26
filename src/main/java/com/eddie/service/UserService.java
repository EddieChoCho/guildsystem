package com.eddie.service;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;

import java.util.List;

public interface UserService extends CrudService<User, Long> {

     User findOneByEmail(String email) throws GuildSystemException;

     List<User> findAllByIdIn(List<Long> idList);
}
