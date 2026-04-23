package com.stealthassassin.states;

import com.stealthassassin.entities.enemies.Enemy;

public interface EnemyState {
    void handle(Enemy enemy, float delta);
    String getStateName();
}
