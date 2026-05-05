package com.stealthassassin.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected Vector2 position;
    protected float health;
    protected float speed;
    protected boolean alive;

    public Entity(float x, float y, float health, float speed) {
        this.position = new Vector2(x, y);
        this.health = health;
        this.speed = speed;
        this.alive = true;
    }

    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);

    public void takeDamage(float damage) {
        this.health -= damage;
        if (health <= 0) alive = false;
    }

    public Vector2 getPosition() { return position; }
    public float getHealth() { return health; }
    public boolean isAlive() { return alive; }
    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }
}
