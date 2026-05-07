package com.stealthassassin.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;


public class Bullet implements Pool.Poolable {

    private Vector2 position;
    private Vector2 velocity;
    private float damage;
    private boolean active;

    public Bullet() {
        position = new Vector2();
        velocity = new Vector2();
        active = false;
    }

    public void init(float x, float y, float vx, float vy, float damage) {
        position.set(x, y);
        velocity.set(vx, vy);
        this.damage = damage;
        this.active = true;
    }

    public void update(float delta) {
        if (active) {
            position.add(velocity.x * delta, velocity.y * delta);
        }
    }

    @Override
    public void reset() {
        position.set(0, 0);
        velocity.set(0, 0);
        damage = 0;
        active = false;
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public Vector2 getPosition() { return position; }
    public float getDamage() { return damage; }
}
