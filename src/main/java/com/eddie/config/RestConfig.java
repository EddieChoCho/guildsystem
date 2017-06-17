package com.eddie.config;

import com.eddie.factory.NpcFactory;
import com.eddie.factory.PlayerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }

    @Bean
    public PlayerFactory playerFactory(){
        return new PlayerFactory();
    }

    @Bean
    public NpcFactory npcFactory(){
        return new NpcFactory();
    }
}
