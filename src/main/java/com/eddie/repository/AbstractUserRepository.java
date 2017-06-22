package com.eddie.repository;
import com.eddie.model.User;

public interface AbstractUserRepository {

    User save(User user);

    User findOne(Long id);

    User findOneByEmail(String email);

    void delete(User user);

}
