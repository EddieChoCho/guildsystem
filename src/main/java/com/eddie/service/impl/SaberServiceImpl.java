package com.eddie.service.impl;

import com.eddie.model.Saber;
import com.eddie.model.User;
import com.eddie.repository.SaberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@Service
@Transactional
public class SaberServiceImpl extends AbstractChampionServiceImpl<Saber> {

    @Autowired
    public SaberServiceImpl(SaberRepository saberRepository) {
        super(saberRepository);
    }

    @Override
    public Saber createChampion(User user, String name, Integer level) {
        Integer hp = level * 100;
        Integer mp = level * 50;
        return new Saber(name,hp,mp,user);
    }
}
