package com.stealthassassin.entities.enemies;

public class Mage extends Enemy {

    private float manaPool;

    public Mage(float x, float y) {
        super(x, y, 60f, 80f, 280f, 35f, "Mage");
        this.manaPool = 100f;
    }

    public void castSpell() {
        if (manaPool >= 20f) {
            manaPool -= 20f;
            System.out.println("Mage sıqyr okıdy!");
        }
    }

    public float getManaPool() { return manaPool; }
}
