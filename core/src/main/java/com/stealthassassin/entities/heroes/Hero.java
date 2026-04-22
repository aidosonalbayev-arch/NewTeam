package com.stealthassassin.entities.heroes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stealthassassin.entities.Entity;

public abstract class Hero extends Entity {

    protected float stealthLevel;
    protected float damage;
    protected String heroName;
    protected float maxHealth;   // <-- JAŃA

    public Hero(float x, float y, float health, float speed,
                float stealthLevel, float damage, String heroName) {
        super(x, y, health, speed);
        this.maxHealth = health;  // <-- JAŃA: bastapqy HP - max HP
        this.stealthLevel = stealthLevel;
        this.damage = damage;
        this.heroName = heroName;
    }

    public abstract void specialAbility();

    @Override
    public void update(float delta) {}

    @Override
    public void render(SpriteBatch batch) {}

    /**
     * HP qosuǵa kómektesedı - biraq maxHealth-ten asyrmaıdy.
     */
    public void heal(float amount) {
        this.health = Math.min(this.health + amount, this.maxHealth);
    }

    /**
     * maxHealth-tıń ózın arttyru (mysaly Iron Skin úshın).
     */
    public void increaseMaxHealth(float amount) {
        this.maxHealth += amount;
        this.health += amount;
    }

    public float getStealthLevel() { return stealthLevel; }
    public void setStealthLevel(float stealthLevel) { this.stealthLevel = stealthLevel; }
    public float getDamage() { return damage; }
    public void setDamage(float damage) { this.damage = damage; }
    public String getHeroName() { return heroName; }
    public float getMaxHealth() { return maxHealth; }
    public void setMaxHealth(float maxHealth) { this.maxHealth = maxHealth; }
}
