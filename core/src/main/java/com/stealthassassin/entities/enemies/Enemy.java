package com.stealthassassin.entities.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stealthassassin.entities.Entity;
import com.stealthassassin.entities.heroes.Hero;
import com.stealthassassin.states.EnemyState;
import com.stealthassassin.states.PatrolState;

public abstract class Enemy extends Entity implements Cloneable {

    protected float detectionRadius;
    protected float damage;
    protected String enemyType;
    protected EnemyState currentState;
    protected float attackCooldown = 0;
    protected float attackRange = 50f;

    public Enemy(float x, float y, float health, float speed,
                 float detectionRadius, float damage, String enemyType) {
        super(x, y, health, speed);
        this.detectionRadius = detectionRadius;
        this.damage = damage;
        this.enemyType = enemyType;
        this.currentState = new PatrolState();
    }

    @Override
    public void update(float delta) {
        if (currentState != null) {
            currentState.handle(this, delta);
        }
        if (attackCooldown > 0) {
            attackCooldown -= delta;
        }
    }

    public boolean tryAttack(Hero hero) {
        if (!alive || attackCooldown > 0) return false;

        float distance = position.dst(hero.getPosition());
        if (distance <= attackRange) {
            hero.takeDamage(damage);
            attackCooldown = 1.2f;
            return true;
        }
        return false;
    }

    @Override
    public void render(SpriteBatch batch) {
    }

    @Override
    public Enemy clone() {
        try {
            Enemy cloned = (Enemy) super.clone();
            cloned.position = this.position.cpy();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone qatesı: " + e.getMessage());
        }
    }

    public void setState(EnemyState state) { this.currentState = state; }
    public EnemyState getState() { return currentState; }
    public float getDetectionRadius() { return detectionRadius; }
    public float getDamage() { return damage; }
    public String getEnemyType() { return enemyType; }
    public float getAttackRange() { return attackRange; }
    public boolean canAttack() { return attackCooldown <= 0; }
}
