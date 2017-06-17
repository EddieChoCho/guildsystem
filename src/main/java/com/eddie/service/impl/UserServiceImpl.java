package com.eddie.service.impl;

import com.eddie.exception.AuthException;
import com.eddie.exception.BasicException;
import com.eddie.model.User;
import com.eddie.repository.UserRepository;
import com.eddie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository repository;

    @Override
    public void add(User user) {
        repository.save(user);
    }

    @Override
    public void edit(User user) {
        repository.save(user);
    }

    @Override
    public User findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public User findOneByEmail(String email) throws BasicException {
        User user = repository.findOneByEmail(email);
        if(user == null){
            throw new AuthException("user.email.notFound");
        }
        return user;
    }

    @Override
    public void checkPassword(User user, String passwrod) throws BasicException {
        if(!user.getPassword().equals(passwrod)){
            throw new AuthException("auth.password.notCorrect");
        }
    }
}
