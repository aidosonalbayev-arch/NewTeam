package com.stealthassassin.factories;

import java.util.List;

import com.stealthassassin.entities.enemies.Enemy;

public abstract class AbstractGameFactory {
    public abstract List<Enemy> createEnemies();
    public abstract int getEnemyCount();
    public abstract String getDifficulty();
}
