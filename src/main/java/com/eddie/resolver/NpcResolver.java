package com.eddie.resolver;

import com.eddie.factory.NpcFactory;
import com.eddie.model.User;
import com.eddie.model.interfaces.NPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class NpcResolver extends AbstractUserResolver implements HandlerMethodArgumentResolver {

    private NpcFactory npcFactory;

    @Autowired
    public NpcResolver(NpcFactory npcFactory){
        this.npcFactory = npcFactory;
    }
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(NPC.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        User user = this.getUserFromSession(nativeWebRequest);
        return npcFactory.provideNpc(user);
    }
}
