package com.eddie.repository;

import com.eddie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
    //TODO validator and Exception
    User findOneByEmail(String email);
}
