package com.eddie.service;


import com.eddie.exception.BasicException;
import com.eddie.model.User;

public interface UserService extends CrudService<User, Long> {
     User findOneByEmail(String email) throws BasicException;
     void checkPassword(User user, String passwrod) throws BasicException;
}
