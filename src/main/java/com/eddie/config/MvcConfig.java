package com.eddie.config;

import com.eddie.resolver.MemberResolver;
import com.eddie.resolver.PlayerResolver;
import com.eddie.resolver.UserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserResolver());
        argumentResolvers.add(new PlayerResolver());
        argumentResolvers.add(new MemberResolver());
    }
}