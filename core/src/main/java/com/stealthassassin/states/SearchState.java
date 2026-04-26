package com.stealthassassin.states;

import com.stealthassassin.entities.enemies.Enemy;

public class SearchState implements EnemyState {

    private float searchTimer = 0;
    private static final float SEARCH_DURATION = 5f;

    @Override
    public void handle(Enemy enemy, float delta) {
        searchTimer += delta;

        // Aınalada qaraıdı
        enemy.getPosition().x += Math.sin(searchTimer * 2) * 20 * delta;

        if (searchTimer >= SEARCH_DURATION) {
            enemy.setState(new PatrolState());
        }
    }

    @Override
    public String getStateName() { return "SEARCH"; }
}
