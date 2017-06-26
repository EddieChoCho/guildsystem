package com.eddie.resolver.arguments;

import com.eddie.factory.NpcFactory;
import com.eddie.model.User;
import com.eddie.model.pojo.GuildManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class GuildManagerResolver extends AbstractUserResolver implements HandlerMethodArgumentResolver {

    private NpcFactory npcFactory;

    @Autowired
    public GuildManagerResolver(NpcFactory npcFactory){
        this.npcFactory = npcFactory;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(GuildManager.class);
    }

    @Override
    public GuildManager resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        User user = this.getUserFromSession(nativeWebRequest);
        return npcFactory.provideGuildManager(user);
    }
}
