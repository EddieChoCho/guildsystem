package com.eddie.integration.om;

import com.eddie.model.Saber;
import com.eddie.model.User;

public class SaberOM {

    public static Saber newSaber(String name, User user){
        return Saber.builder()
                .name(name)
                .user(user)
                .build();
    }
}
