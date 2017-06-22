package com.eddie.model.pojo;

import com.eddie.model.enums.Role;

public class Leader extends Member {
    public Leader(Long id, String name, String email, Role role) {
        super(id, name, email, role);
    }
}
