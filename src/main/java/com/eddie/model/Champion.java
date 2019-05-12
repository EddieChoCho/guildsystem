package com.eddie.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by EddieChoCho on 2017/9/28.
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="champion_type", discriminatorType = DiscriminatorType.INTEGER)
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

}
