package com.stealthassassin.states;

import com.badlogic.gdx.math.Vector2;
import com.stealthassassin.entities.enemies.Enemy;

public class ChaseState implements EnemyState {

    private Vector2 targetPosition;

    public ChaseState(Vector2 targetPosition) {
        this.targetPosition = targetPosition;
    }

    @Override
    public void handle(Enemy enemy, float delta) {
        Vector2 direction = new Vector2(targetPosition).sub(enemy.getPosition()).nor();
        enemy.getPosition().add(direction.scl(enemy.getSpeed() * 1.5f * delta));

        if (enemy.getPosition().dst(targetPosition) < 50f) {
            enemy.setState(new AttackState(targetPosition));
        }
    }

    @Override
    public String getStateName() { return "CHASE"; }
}
