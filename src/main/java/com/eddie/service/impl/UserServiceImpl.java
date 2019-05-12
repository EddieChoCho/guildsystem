package com.eddie.service.impl;

import com.eddie.exception.GuildSystemException;
import com.eddie.exception.UserException;
import com.eddie.model.User;
import com.eddie.repository.UserRepository;
import com.eddie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends CrudServiceImpl<User, Long> implements UserService{

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository){
        super(repository);
        this.repository = repository;
    }

    @Override
    public User add(User user) throws GuildSystemException {
        this.checkEmailHasBeenUsed(user.getEmail());
        return super.add(user);
    }

    @Override
    public User findById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public User findOneByEmail(String email) {
        return repository.findOneByEmail(email);
    }

    @Override
    public List<User> findAllByIdIn(List<Long> idList) {
        return repository.findAllByIdIn(idList);
    }

    private void checkEmailHasBeenUsed(String email) throws UserException {
        if(this.findOneByEmail(email) != null){
            throw new UserException("user.email.hasBeenUsed");
        }

    }

}

