package com.eddie.repository;

import com.eddie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findAllByIdIn(List<Long> id);

    User findOneByEmail(String email);

    User findOneByEmailAndPassword(String email,String password);

    @Query("SELECT user FROM User user WHERE user.name IS NOT NULL AND user.name <> '' ")
    List<User> findUserWithNotEmptyName();
}
