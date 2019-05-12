package com.eddie.model;

import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@Entity
@DiscriminatorValue("1")
public class Saber extends Champion {

    public Saber(){

    }

    @Builder
    public Saber(String name, Integer healthPoint, Integer magicPoint, User user) {
        super(name, healthPoint, magicPoint, user);
    }
}
