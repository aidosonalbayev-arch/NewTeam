package com.stealthassassin.managers;

import com.stealthassassin.entities.enemies.Enemy;
import com.stealthassassin.entities.heroes.Hero;
import com.stealthassassin.states.ChaseState;
import com.stealthassassin.states.SearchState;

import java.util.List;

public class DetectionManager {

    public void checkDetection(Hero hero, List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) continue;

            float distance = enemy.getPosition().dst(hero.getPosition());
            float effectiveRadius = enemy.getDetectionRadius() * (1f - hero.getStealthLevel());

            if (enemy.isAggressive()) {
                enemy.rememberTarget(hero);
                if (!(enemy.getState() instanceof ChaseState)) {
                    enemy.setState(new ChaseState(hero.getPosition()));
                }
                continue;
            }

            if (distance < effectiveRadius) {
                enemy.rememberTarget(hero);
                if (!(enemy.getState() instanceof ChaseState)) {
                    enemy.setState(new ChaseState(hero.getPosition()));
                    System.out.println(enemy.getEnemyType() + " qaharmandy bayqady!");
                }
            } else if (distance < effectiveRadius * 1.5f
                && enemy.getState() instanceof ChaseState
                && !enemy.isAggressive()) {
                enemy.setState(new SearchState());
            }
        }
    }
}
