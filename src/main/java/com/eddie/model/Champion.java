package com.eddie.model;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@MappedSuperclass
public class Champion extends AbstractEntity{

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    private Integer healthPoint;

    private Integer magicPoint;

    @ManyToOne
    private User user;

    public Champion(){

    }

    public Champion(String name, Integer healthPoint, Integer magicPoint, User user){
        this.name = name;
        this.healthPoint = healthPoint;
        this.magicPoint = magicPoint;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(Integer healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Integer getMagicPoint() {
        return magicPoint;
    }

    public void setMagicPoint(Integer magicPoint) {
        this.magicPoint = magicPoint;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
