package com.stealthassassin.entities.enemies;

public class Boss extends Enemy {

    private int phase;

    public Boss(float x, float y) {
        super(x, y, 300f, 120f, 400f, 60f, "Boss");
        this.phase = 1;
    }

    @Override
    public void takeDamage(float damage) {
        super.takeDamage(damage);
        if (health < 250f && phase == 1) {
            phase = 2;
            speed *= 1.5f;
        }
    }

    public int getPhase() { return phase; }
}
