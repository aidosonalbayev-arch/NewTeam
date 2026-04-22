package com.stealthassassin.facade;

import com.stealthassassin.builders.Level;
import com.stealthassassin.builders.LevelBuilder;
import com.stealthassassin.entities.enemies.Enemy;
import com.stealthassassin.entities.heroes.Hero;
import com.stealthassassin.factories.AbstractGameFactory;
import com.stealthassassin.factories.EasyLevelFactory;
import com.stealthassassin.factories.EnemyFactory;
import com.stealthassassin.factories.HardLevelFactory;
import com.stealthassassin.factories.HeroFactory;
import com.stealthassassin.observers.GameEventManager;
import com.stealthassassin.observers.ScoreObserver;

public class GameFacade {

    private final HeroFactory heroFactory;
    private final EnemyFactory enemyFactory;
    private final GameEventManager eventManager;

    private Hero currentHero;
    private Level currentLevel;

    public GameFacade() {
        this.heroFactory = new HeroFactory();
        this.enemyFactory = new EnemyFactory();
        this.eventManager = GameEventManager.getInstance();

        eventManager.subscribe(new ScoreObserver());
    }

    public void startNewGame(HeroFactory.HeroType heroType, String difficulty) {
        currentHero = heroFactory.createHero(heroType, 150, 150);

        AbstractGameFactory levelFactory;
        if ("HARD".equals(difficulty)) {
            levelFactory = new HardLevelFactory();
        } else {
            levelFactory = new EasyLevelFactory();
        }

        currentLevel = new LevelBuilder()
            .setName("Level 1 - " + difficulty)
            .setMap("maps/level1.tmx")
            .addEnemies(levelFactory.createEnemies())
            .setDifficulty(levelFactory.getDifficulty().equals("HARD") ? 3 : 1)
            .enableNightMode()
            .setFog(0.5f)
            .build();

        System.out.println("Oıyn bastaldy! Qaharman: " + currentHero.getHeroName()
            + ", Deńgeı: " + currentLevel.getLevelName());
    }

    public void onEnemyKilled(Enemy enemy) {
        eventManager.notify("ENEMY_KILLED", enemy.getEnemyType());
    }

    public void onStealthKill() {
        eventManager.notify("STEALTH_KILL", null);
    }

    public Hero getCurrentHero() { return currentHero; }
    public Level getCurrentLevel() { return currentLevel; }
}
