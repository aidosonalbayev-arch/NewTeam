package com.stealthassassin.factories;

import java.util.ArrayList;
import java.util.List;

import com.stealthassassin.entities.enemies.Enemy;

public class HardLevelFactory extends AbstractGameFactory {

    private final EnemyFactory enemyFactory = new EnemyFactory();

    @Override
    public List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemyFactory.createEnemy(EnemyFactory.EnemyType.KNIGHT, 400, 300));
        enemies.add(enemyFactory.createEnemy(EnemyFactory.EnemyType.MAGE, 700, 500));
        enemies.add(enemyFactory.createEnemy(EnemyFactory.EnemyType.ARCHER, 900, 200));
        enemies.add(enemyFactory.createEnemy(EnemyFactory.EnemyType.KNIGHT, 1000, 550));
        enemies.add(enemyFactory.createEnemy(EnemyFactory.EnemyType.BOSS, 640, 360));
        return enemies;
    }

    @Override
    public int getEnemyCount() { return 5; }

    @Override
    public String getDifficulty() { return "HARD"; }
}
