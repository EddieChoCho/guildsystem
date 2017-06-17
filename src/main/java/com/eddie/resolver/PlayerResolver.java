package com.eddie.resolver;

import com.eddie.factory.PlayerFactory;
import com.eddie.model.User;
import com.eddie.model.interfaces.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PlayerResolver extends AbstractUserResolver implements HandlerMethodArgumentResolver {

    @Autowired
    PlayerFactory playerFactory;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Player.class);
    }

    @Override
    public Player resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        User user = this.getUserFromSession(nativeWebRequest);
        return playerFactory.factorPlayer(user);
    }
}
