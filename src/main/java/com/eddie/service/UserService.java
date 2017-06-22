package com.eddie.service;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;

public interface UserService extends CrudService<User, Long> {

     User findOneByEmail(String email) throws GuildSystemException;
}
