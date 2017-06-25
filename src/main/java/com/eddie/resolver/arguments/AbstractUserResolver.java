package com.eddie.resolver.arguments;

import com.eddie.exception.AuthException;
import com.eddie.exception.GuildSystemException;
import com.eddie.model.User;
import org.springframework.web.context.request.NativeWebRequest;

public abstract class AbstractUserResolver {
    public User getUserFromSession(NativeWebRequest nativeWebRequest) throws GuildSystemException {
        User user = (User) nativeWebRequest.getAttribute("user",1);
        if(user == null){
            throw new AuthException("auth.notLogin");
        }
        return user;
    }
}
