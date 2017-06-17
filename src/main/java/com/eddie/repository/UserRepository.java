package com.eddie.repository;

import com.eddie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ASUS on 2017/6/17.
 */
public interface UserRepository extends JpaRepository<User, Long>{
}
