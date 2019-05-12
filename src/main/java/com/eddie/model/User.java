package com.eddie.model;

import com.eddie.model.enums.BasicRole;
import com.eddie.model.enums.Role;
import com.eddie.model.interfaces.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@Entity
@JsonIgnoreProperties({ "password" })
public class User extends AbstractEntity implements Person{
    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(){

    }

    @Builder
    public User(String name, String email, String password, Role role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public BasicRole getBasicRole(){
        return this.role.getBasicRole();
    }
}
