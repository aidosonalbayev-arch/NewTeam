package com.stealthassassin.entities.heroes;

public class Rogue extends Hero {

    private boolean abilityUsed = false;  // <-- JAŃA: 1 ret qana!

    public Rogue(float x, float y) {
        super(x, y, 200f, 150f, 0.7f, 45f, "Rogue");
    }

    @Override
    public void specialAbility() {
        if (abilityUsed) {
            System.out.println("Iron Skin ázır qoldanylǵan!");
            return;
        }
        // max HP-dy 170-ke deıın arttyrady (120 + 50 = 170)
        increaseMaxHealth(50);
        abilityUsed = true;
        System.out.println(heroName + " Iron Skin-dı qosty! Max HP: " + getMaxHealth());
    }
}
