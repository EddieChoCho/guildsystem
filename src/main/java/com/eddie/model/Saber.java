package com.eddie.model;

import javax.persistence.Entity;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@Entity
public class Saber extends Champion {

    public Saber(){

    }

    public Saber(String name, Integer healthPoint, Integer magicPoint, User user) {
        super(name, healthPoint, magicPoint, user);
    }
}
