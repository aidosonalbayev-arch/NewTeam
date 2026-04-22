package com.stealthassassin;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stealthassassin.screens.MainMenuScreen;

public class StealthAssassinGame extends Game {

    private static StealthAssassinGame instance;
    private SpriteBatch batch;
    private int playerScore;

    private StealthAssassinGame() {
    }

    public static StealthAssassinGame getInstance() {
        if (instance == null) {
            instance = new StealthAssassinGame();
        }
        return instance;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        playerScore = 0;
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public SpriteBatch getBatch() { return batch; }
    public int getPlayerScore() { return playerScore; }
    public void addScore(int points) { this.playerScore += points; }
    public void resetScore() { this.playerScore = 0; }
}
