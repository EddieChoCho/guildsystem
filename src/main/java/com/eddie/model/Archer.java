package com.eddie.model;

import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@Entity
@DiscriminatorValue("2")
public class Archer extends Champion {

    public Archer(){

    }

    @Builder
    public Archer(String name, Integer healthPoint, Integer magicPoint, User user) {
        super(name, healthPoint, magicPoint, user);
    }
}
