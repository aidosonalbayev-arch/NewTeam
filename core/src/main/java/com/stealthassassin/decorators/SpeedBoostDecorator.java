package com.stealthassassin.decorators;

import com.stealthassassin.entities.heroes.Hero;

public class SpeedBoostDecorator extends HeroDecorator {

    public SpeedBoostDecorator(Hero hero) {
        super(hero);
        this.speed = decoratedHero.getSpeed() * 1.3f;
        decoratedHero.setSpeed(this.speed);
    }
}
