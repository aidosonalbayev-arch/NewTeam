package com.stealthassassin.pool;

import com.badlogic.gdx.utils.Pool;
import com.stealthassassin.utils.Bullet;

public class BulletPool extends Pool<Bullet> {

    private static BulletPool instance;

    private BulletPool() {
        super(50, 200); // bastapqı sany, eń úlken sany
    }

    public static BulletPool getInstance() {
        if (instance == null) {
            instance = new BulletPool();
        }
        return instance;
    }

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
