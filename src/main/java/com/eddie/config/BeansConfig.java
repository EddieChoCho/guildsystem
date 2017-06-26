package com.eddie.config;

import com.eddie.factory.NpcFactory;
import com.eddie.factory.PlayerFactory;
import com.eddie.model.User;
import com.eddie.response.impl.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

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

    @Bean
    public DataResponse<User> userResponse(){
        return new DataResponse<User>(mapper());
    }


}
