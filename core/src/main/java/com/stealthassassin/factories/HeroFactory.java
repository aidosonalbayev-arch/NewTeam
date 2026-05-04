package com.stealthassassin.factories;

import com.stealthassassin.entities.heroes.Hero;
import com.stealthassassin.entities.heroes.Ninja;
import com.stealthassassin.entities.heroes.Rogue;
import com.stealthassassin.entities.heroes.Shadow;

public class HeroFactory {

    public enum HeroType {
        NINJA, SHADOW, ROGUE
    }

    public Hero createHero(HeroType type, float x, float y) {
        switch (type) {
            case NINJA:  return new Ninja(x, y);
            case SHADOW: return new Shadow(x, y);
            case ROGUE:  return new Rogue(x, y);
            default:
                throw new IllegalArgumentException("Belgisiz qaharman túri: " + type);
        }
    }
}
