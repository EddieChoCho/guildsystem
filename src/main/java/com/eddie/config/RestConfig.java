package com.eddie.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2017/6/17.
 */
@Configuration
public class RestConfig {

    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }
}
