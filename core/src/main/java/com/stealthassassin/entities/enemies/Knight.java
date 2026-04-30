package com.stealthassassin.entities.enemies;

public class Knight extends Enemy {

    private float armor;

    public Knight(float x, float y) {
        super(x, y, 120f, 70f, 180f, 30f, "Knight");
        this.armor = 0.3f;
    }

    @Override
    public void takeDamage(float damage) {
        super.takeDamage(damage * (1f - armor));
    }
}
