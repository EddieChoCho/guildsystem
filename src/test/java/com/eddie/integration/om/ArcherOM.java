package com.eddie.integration.om;

import com.eddie.model.Archer;
import com.eddie.model.User;

public class ArcherOM {

    public static Archer newArcher(String name, User user){
        return Archer.builder()
                .name(name)
                .user(user)
                .build();
    }
}
