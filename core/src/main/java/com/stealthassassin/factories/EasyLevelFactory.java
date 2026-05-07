package com.stealthassassin.factories;

import java.util.ArrayList;
import java.util.List;

import com.stealthassassin.entities.enemies.Enemy;

public class EasyLevelFactory extends AbstractGameFactory {

    private final EnemyFactory enemyFactory = new EnemyFactory();

    @Override
    public List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemyFactory.createEnemy(EnemyFactory.EnemyType.GUARD, 500, 400));
        enemies.add(enemyFactory.createEnemy(EnemyFactory.EnemyType.GUARD, 800, 500));
        enemies.add(enemyFactory.createEnemy(EnemyFactory.EnemyType.ARCHER, 1000, 300));
        return enemies;
    }

    @Override
    public int getEnemyCount() { return 3; }

    @Override
    public String getDifficulty() { return "EASY"; }
}
