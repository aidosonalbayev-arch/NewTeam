package com.stealthassassin.states;

import com.stealthassassin.entities.enemies.Enemy;

public class PatrolState implements EnemyState {

    private float patrolTimer = 0;
    private int direction = 1;

    @Override
    public void handle(Enemy enemy, float delta) {
        patrolTimer += delta;
        // Patrul logikasy
        enemy.getPosition().x += direction * enemy.getSpeed() * delta;

        if (patrolTimer > 3f) {
            direction *= -1;
            patrolTimer = 0;
        }
    }

    @Override
    public String getStateName() { return "PATROL"; }
}
