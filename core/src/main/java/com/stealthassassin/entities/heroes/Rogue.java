package com.stealthassassin.entities.heroes;

public class Rogue extends Hero {

    private boolean abilityUsed = false;  // <-- JAŃA: 1 ret qana!

    public Rogue(float x, float y) {
        super(x, y, 200f, 150f, 0.7f, 45f, "Rogue");
    }

    @Override
    public void specialAbility() {
        if (abilityUsed) {
            return;
        }
        increaseMaxHealth(50);
        abilityUsed = true;
    }
}
