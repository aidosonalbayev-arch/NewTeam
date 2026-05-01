package com.stealthassassin.decorators;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stealthassassin.entities.heroes.Hero;

public abstract class HeroDecorator extends Hero {

    protected Hero decoratedHero;

    public HeroDecorator(Hero hero) {
        super(hero.getPosition().x, hero.getPosition().y,
            hero.getHealth(), hero.getSpeed(),
            hero.getStealthLevel(), hero.getDamage(),
            hero.getHeroName());
        this.decoratedHero = hero;
        this.maxHealth = hero.getMaxHealth();  // <-- JAŃA
    }

    @Override
    public void update(float delta) {
        decoratedHero.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        decoratedHero.render(batch);
    }

    @Override
    public void specialAbility() {
        decoratedHero.specialAbility();
    }
}
