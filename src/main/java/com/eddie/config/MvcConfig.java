package com.eddie.config;

import com.eddie.resolver.MemberResolver;
import com.eddie.resolver.NpcResolver;
import com.eddie.resolver.PlayerResolver;
import com.eddie.resolver.UserResolver;
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