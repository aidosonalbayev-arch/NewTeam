package com.stealthassassin.decorators;

import com.stealthassassin.entities.heroes.Hero;

public class StealthBoostDecorator extends HeroDecorator {

    public StealthBoostDecorator(Hero hero) {
        super(hero);
        this.stealthLevel = decoratedHero.getStealthLevel() * 1.5f;
        decoratedHero.setStealthLevel(this.stealthLevel);
    }
}
