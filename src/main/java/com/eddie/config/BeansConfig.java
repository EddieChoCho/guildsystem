package com.eddie.config;

import com.eddie.factory.NpcFactory;
import com.eddie.factory.PlayerFactory;
import com.eddie.model.Archer;
import com.eddie.model.Saber;
import com.eddie.model.Team;
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

    @Bean
    public DataResponse<Team> teamResponse(){
        return new DataResponse<Team>(mapper());
    }

    @Bean
    public DataResponse<Saber> saberResponse(){
        return new DataResponse<Saber>(mapper());
    }

    @Bean
    public DataResponse<Archer> archerResponse(){
        return new DataResponse<Archer>(mapper());
    }


}
