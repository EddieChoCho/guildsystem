package com.eddie.config;

import com.eddie.builder.UserBuilder;
import com.eddie.factory.NpcFactory;
import com.eddie.factory.PlayerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

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

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/ExceptionMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
