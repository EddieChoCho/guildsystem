package com.eddie.model;

import javax.persistence.Entity;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@Entity
public class Archer extends Champion {

    public Archer(){

    }

    public Archer(String name, Integer healthPoint, Integer magicPoint, User user) {
        super(name, healthPoint, magicPoint, user);
    }
}
