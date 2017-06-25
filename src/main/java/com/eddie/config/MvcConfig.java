package com.eddie.config;

import com.eddie.resolver.arguments.MemberResolver;
import com.eddie.resolver.arguments.NpcResolver;
import com.eddie.resolver.arguments.PlayerResolver;
import com.eddie.resolver.arguments.UserResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    UserResolver userResolver;
    @Autowired
    PlayerResolver playerResolver;
    @Autowired
    MemberResolver memberResolver;
    @Autowired
    NpcResolver npcResolver;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userResolver);
        argumentResolvers.add(playerResolver);
        argumentResolvers.add(memberResolver);
        argumentResolvers.add(npcResolver);
    }
}