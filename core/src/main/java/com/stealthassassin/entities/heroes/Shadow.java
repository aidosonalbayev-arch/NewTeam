package com.stealthassassin.entities.heroes;

public class Shadow extends Hero {

    private boolean abilityUsed = false;
    private float abilityTimer = 0;
    private float originalStealth;

    public Shadow(float x, float y) {
        super(x, y, 150f, 180f, 1.0f, 25f, "Shadow");
        this.originalStealth = this.stealthLevel;
    }

    @Override
    public void specialAbility() {
        if (abilityUsed) {
            return;
        }
        this.stealthLevel = 2.0f;
        abilityUsed = true;
        abilityTimer = 5f;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (abilityUsed) {
            abilityTimer -= delta;
            if (abilityTimer <= 0) {
                this.stealthLevel = originalStealth;
                abilityUsed = false;
            }
        }
    }
}
