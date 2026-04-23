package com.stealthassassin.entities.heroes;

public class Ninja extends Hero {

    private boolean abilityUsed = false;  // <-- JAŃA: 1 ret qana qoldanuǵa bolady
    private float abilityTimer = 0;

    public Ninja(float x, float y) {
        super(x, y, 250f, 200f, 0.9f, 30f, "Ninja");
    }

    @Override
    public void specialAbility() {
        if (abilityUsed) {
            System.out.println("Shadow Strike ázır daıyn emes!");
            return;
        }
        System.out.println(heroName + " Shadow Strike-tı qoldandy! Damage x2 - 5 sekund");
        this.damage *= 2;
        abilityUsed = true;
        abilityTimer = 5f;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (abilityUsed) {
            abilityTimer -= delta;
            if (abilityTimer <= 0) {
                this.damage /= 2;  // Qaıtarylady
                abilityUsed = false;
                System.out.println("Shadow Strike aıaqtaldy");
            }
        }
    }
}
