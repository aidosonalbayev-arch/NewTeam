package com.stealthassassin.builders;

import com.stealthassassin.entities.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

public class LevelBuilder {

    private final Level level;

    public LevelBuilder() {
        this.level = new Level();
        this.level.setEnemies(new ArrayList<>());
    }

    public LevelBuilder setName(String name) {
        level.setLevelName(name);
        return this;
    }

    public LevelBuilder setMap(String mapPath) {
        level.setMapPath(mapPath);
        return this;
    }

    public LevelBuilder addEnemy(Enemy enemy) {
        level.getEnemies().add(enemy);
        return this;
    }

    public LevelBuilder addEnemies(List<Enemy> enemies) {
        level.getEnemies().addAll(enemies);
        return this;
    }

    public LevelBuilder setDifficulty(int difficulty) {
        level.setDifficulty(difficulty);
        return this;
    }

    public LevelBuilder enableNightMode() {
        level.setNightMode(true);
        return this;
    }

    public LevelBuilder setFog(float intensity) {
        level.setFogIntensity(intensity);
        return this;
    }

    public Level build() {
        return level;
    }
}
