package com.eddie.repository_abstract;

import com.eddie.model.Champion;
import com.eddie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
public interface ChampionRepository<C extends Champion> extends JpaRepository<C, Long> {

    List<C> findAllByUser(User user);

    C findOneByNameAndUser(String name, User user);
}
