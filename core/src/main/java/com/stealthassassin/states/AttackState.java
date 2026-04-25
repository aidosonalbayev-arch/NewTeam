package com.stealthassassin.states;

import com.badlogic.gdx.math.Vector2;
import com.stealthassassin.entities.enemies.Enemy;

public class AttackState implements EnemyState {

    private Vector2 targetPosition;
    private float attackCooldown = 0;

    public AttackState(Vector2 targetPosition) {
        this.targetPosition = targetPosition;
    }

    @Override
    public void handle(Enemy enemy, float delta) {
        attackCooldown -= delta;
        if (attackCooldown <= 0) {
            System.out.println(enemy.getEnemyType() + " shabuıl jasaıdy! Zalal: " + enemy.getDamage());
            attackCooldown = 1f;
        }

        if (enemy.getPosition().dst(targetPosition) > 100f) {
            enemy.setState(new ChaseState(targetPosition));
        }
    }

    @Override
    public String getStateName() { return "ATTACK"; }
}
