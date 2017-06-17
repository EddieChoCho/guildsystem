package com.eddie.model.enums;

/**
 * Created by ASUS on 2017/6/17.
 */
public enum Role {
    MEMBER(BasicRole.PLAYER),
    LEADER(BasicRole.PLAYER),
    PARTNER(BasicRole.NPC),
    MANAGER(BasicRole.NPC);

    private BasicRole basicRole;

    public BasicRole getBasicRole() {
        return basicRole;
    }

    public void setBasicRole(BasicRole basicRole) {
        this.basicRole = basicRole;
    }

    Role(BasicRole basicRole){
        this.basicRole = basicRole;
    }
}
