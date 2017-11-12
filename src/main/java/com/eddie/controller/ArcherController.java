package com.eddie.controller;

import com.eddie.exception.GuildSystemException;
import com.eddie.model.Archer;
import com.eddie.model.User;
import com.eddie.response.impl.DataResponse;
import com.eddie.service.ChampionService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@RestController
@RequestMapping("/rest/archers")
public class ArcherController extends ChampionController<Archer> {

    @Autowired
    public ArcherController(ChampionService<Archer> championService, DataResponse<Archer> archerDataResponse) {
        super(championService, archerDataResponse);
    }

    @PostMapping("/")
    public JsonNode createChampion(User user,
                                   @RequestParam(value = "name") String name,
                                   @RequestParam(value = "level") Integer level) throws GuildSystemException {
        return super.createChampion(user,name,level);
    }

    @GetMapping("/")
    public JsonNode findAllChampionsOfUser(User user) {
        return super.findAllChampionsOfUser(user);
    }

    @GetMapping("/{name}")
    public JsonNode findChampionByNameAndUser(User user, @PathVariable String name) {
        return super.findChampionByNameAndUser(user, name);
    }
}
