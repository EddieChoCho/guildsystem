package com.eddie.factory;

import com.eddie.exception.GuildSystemException;
import com.eddie.exception.RoleException;
import com.eddie.model.enums.BasicRole;
import com.eddie.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleCheckHelper {

    public void roleChecking(Role userRole, Role roleShouldBe) throws GuildSystemException {
        if(!userRole.equals(roleShouldBe)){
            throw new RoleException("accessDenied.roleNotFit." + roleShouldBe.name());
        }
    }

    public void roleChecking(BasicRole userRole, BasicRole roleShouldBe) throws GuildSystemException {
        if(!userRole.equals(roleShouldBe)){
            throw new RoleException("accessDenied.roleNotFit." + roleShouldBe.name() );
        }
    }
}
