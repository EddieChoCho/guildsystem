package com.eddie.controller;

import com.eddie.model.User;
import com.eddie.model.interfaces.NPC;
import com.eddie.model.interfaces.Player;
import com.eddie.model.pojo.Member;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/test/")
public class TestController {

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("me/")
    public User checkUserInfo(User user){
        return user;
    }

    @GetMapping("me/player")
    public JsonNode checkUserInfo(Player player){
        return mapper.createObjectNode().put("message",player.getName() +"! you are a player!");
    }

    @GetMapping("me/npc")
    public JsonNode checkUserInfo(NPC npc){
        return mapper.createObjectNode().put("message",npc.getName() +"! you are a NPC!");
    }

    @GetMapping("me/member")
    public JsonNode checkUserInfo(Member member){
        return mapper.createObjectNode().put("message",member.getName() +"!you are a member!");
    }
}
