package com.eddie.model.interfaces;

import java.util.List;

public abstract class Technique implements Skill {

    private List<Skill> skills;

    protected Technique(List<Skill> skills) {
        this.skills = skills;
    }

    public void setSkills(List<Skill> skills){
        this.skills = skills;
    }

    public void addSkills(List<Skill> skills){
        this.skills.addAll(skills);
    }

    public void addSkill(Skill skill){
        this.skills.add(skill);
    }
    @Override
    public Creature use(Creature target) {
        for(Skill skill : skills){
            target = skill.use(target);
        }
        return target;
    }
}
