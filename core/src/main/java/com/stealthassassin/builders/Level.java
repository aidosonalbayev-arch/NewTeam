package com.stealthassassin.builders;

import com.stealthassassin.entities.enemies.Enemy;

import java.util.List;

public class Level {
    private String levelName;
    private String mapPath;
    private List<Enemy> enemies;
    private int difficulty;
    private boolean nightMode;
    private float fogIntensity;

    public void setLevelName(String levelName) { this.levelName = levelName; }
    public void setMapPath(String mapPath) { this.mapPath = mapPath; }
    public void setEnemies(List<Enemy> enemies) { this.enemies = enemies; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }
    public void setNightMode(boolean nightMode) { this.nightMode = nightMode; }
    public void setFogIntensity(float fogIntensity) { this.fogIntensity = fogIntensity; }

    public String getLevelName() { return levelName; }
    public String getMapPath() { return mapPath; }
    public List<Enemy> getEnemies() { return enemies; }
    public int getDifficulty() { return difficulty; }
    public boolean isNightMode() { return nightMode; }
    public float getFogIntensity() { return fogIntensity; }
}
