package com.eddie.repository;

import com.eddie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends AbstractUserRepository, JpaRepository<User, Long>{

}
