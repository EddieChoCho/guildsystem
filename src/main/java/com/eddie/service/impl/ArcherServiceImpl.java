package com.eddie.service.impl;

import com.eddie.model.Archer;
import com.eddie.model.User;
import com.eddie.repository.ArcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@Service
@Transactional
public class ArcherServiceImpl extends AbstractChampionServiceImpl<Archer> {

    @Autowired
    public ArcherServiceImpl(ArcherRepository archerRepository) {
        super(archerRepository);
    }

    @Override
    public Archer createChampion(User user, String name, Integer level) {
        Integer hp = level * 50;
        Integer mp = level * 100;
        return new Archer(name,hp,mp,user);
    }
}
