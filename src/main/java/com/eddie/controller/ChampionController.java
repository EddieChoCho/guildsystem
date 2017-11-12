package com.eddie.controller;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.Champion;
import com.eddie.model.User;
import com.eddie.response.impl.DataResponse;
import com.eddie.service.ChampionService;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
public abstract class ChampionController<C extends Champion> {

    private ChampionService<C> championService;

    private DataResponse<C> championResponse;

    public ChampionController(ChampionService<C> championService, DataResponse<C> championResponse){
        this.championService = championService;
        this.championResponse = championResponse;
    }

    public JsonNode createChampion(User user, String name, Integer level) throws GuildSystemException {
        C champion = championService.createChampion(user,name,level);
        championService.add(champion);
        return championResponse.messageResponse();
    }

    public JsonNode findAllChampionsOfUser(User user) {
        List<C> champions = championService.findAllByUser(user);
        return championResponse.packResponse(champions);
    }

    public JsonNode findChampionByNameAndUser(User user,String name) {
        C champion = championService.findOneByNameAndUser(name, user);
        return championResponse.packResponse(champion);
    }
}
