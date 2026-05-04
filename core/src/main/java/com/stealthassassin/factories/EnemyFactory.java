package com.stealthassassin.factories;

import com.stealthassassin.entities.enemies.Archer;
import com.stealthassassin.entities.enemies.Boss;
import com.stealthassassin.entities.enemies.Enemy;
import com.stealthassassin.entities.enemies.Guard;
import com.stealthassassin.entities.enemies.Knight;
import com.stealthassassin.entities.enemies.Mage;

public class EnemyFactory {

    public enum EnemyType {
        GUARD, ARCHER, KNIGHT, MAGE, BOSS
    }

    public Enemy createEnemy(EnemyType type, float x, float y) {
        switch (type) {
            case GUARD:  return new Guard(x, y);
            case ARCHER: return new Archer(x, y);
            case KNIGHT: return new Knight(x, y);
            case MAGE:   return new Mage(x, y);
            case BOSS:   return new Boss(x, y);
            default:
                throw new IllegalArgumentException("Belgisiz jau túri: " + type);
        }
    }
}
