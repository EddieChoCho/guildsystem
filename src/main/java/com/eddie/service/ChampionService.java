package com.eddie.service;

import com.eddie.model.Champion;
import com.eddie.model.User;

import java.util.List;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
public interface ChampionService<C extends Champion> extends CrudService<C, Long>{

    C createChampion(User user, String name, Integer level);

    List<C> findAllByUser(User user);

    C findOneByNameAndUser(String name, User user);
}
