package com.eddie.repository;

import com.eddie.model.Archer;
import com.eddie.repository_abstract.ChampionRepository;

import javax.transaction.Transactional;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@Transactional
public interface ArcherRepository extends ChampionRepository<Archer> {
}
