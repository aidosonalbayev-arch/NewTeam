package com.stealthassassin.decorators;

import com.stealthassassin.entities.heroes.Hero;

public class DamageBoostDecorator extends HeroDecorator {

    public DamageBoostDecorator(Hero hero) {
        super(hero);
        this.damage = decoratedHero.getDamage() * 1.4f;
        decoratedHero.setDamage(this.damage);
    }
}
