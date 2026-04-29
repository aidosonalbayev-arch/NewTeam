package com.stealthassassin.entities.enemies;

public class Archer extends Enemy {

    private float attackRange;

    public Archer(float x, float y) {
        super(x, y, 40f, 90f, 250f, 20f, "Archer");
        this.attackRange = 300f;
    }

    public float getAttackRange() { return attackRange; }
}
