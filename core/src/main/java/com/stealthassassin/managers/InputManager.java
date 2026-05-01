package com.stealthassassin.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.stealthassassin.entities.heroes.Hero;

public class InputManager {

    public void handleInput(Hero hero, float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            hero.getPosition().y += hero.getSpeed() * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            hero.getPosition().y -= hero.getSpeed() * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            hero.getPosition().x -= hero.getSpeed() * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            hero.getPosition().x += hero.getSpeed() * delta;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            hero.specialAbility();
        }
    }
}
