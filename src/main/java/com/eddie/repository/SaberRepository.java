package com.eddie.repository;

import com.eddie.model.Saber;
import com.eddie.repository_abstract.ChampionRepository;

import javax.transaction.Transactional;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@Transactional
public interface SaberRepository extends ChampionRepository<Saber> {
}
