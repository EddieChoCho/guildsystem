package com.eddie.service.impl;

import com.eddie.model.Champion;
import com.eddie.model.User;
import com.eddie.repository_abstract.ChampionRepository;
import com.eddie.service.ChampionService;

import java.util.List;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
public abstract class ChampionServiceImpl<C extends Champion> implements ChampionService<C> {

    private ChampionRepository<C> championRepository;

    public ChampionServiceImpl(ChampionRepository championRepository){
        this.championRepository = championRepository;
    }

    @Override
    public C add(C c) {
        return championRepository.save(c);
    }

    @Override
    public C edit(C c) {
        return championRepository.save(c);
    }

    @Override
    public C findById(Long id) {
        return championRepository.findOne(id);
    }

    @Override
    public void delete(C c) {
        championRepository.delete(c);
    }

    @Override
    public List<C> findAllByUser(User user) {
        return championRepository.findAllByUser(user);
    }

    @Override
    public C findOneByNameAndUser(String name, User user) {
        return championRepository.findOneByNameAndUser(name,user);
    }

    public ChampionRepository<C> getChampionRepository() {
        return championRepository;
    }
}
