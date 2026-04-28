package com.stealthassassin.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stealthassassin.StealthAssassinGame;

public class GameOverScreen implements Screen {

    private final StealthAssassinGame game;
    private final boolean victory;
    private final SpriteBatch batch;
    private final BitmapFont bigFont;
    private final BitmapFont smallFont;

    public GameOverScreen(StealthAssassinGame game, boolean victory) {
        this.game = game;
        this.victory = victory;
        this.batch = new SpriteBatch();

        this.bigFont = new BitmapFont();
        this.bigFont.getData().setScale(5f);
        this.bigFont.setColor(victory ? Color.GREEN : Color.RED);

        this.smallFont = new BitmapFont();
        this.smallFont.getData().setScale(2f);
        this.smallFont.setColor(Color.WHITE);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        if (victory) {
            Gdx.gl.glClearColor(0, 0.15f, 0, 1);
        } else {
            Gdx.gl.glClearColor(0.15f, 0, 0, 1);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        String mainText = victory ? "Win!" : "Game Over";
        bigFont.draw(batch, mainText, 480, 500);

        smallFont.draw(batch, "Finaldyk esep: " + game.getPlayerScore(), 450, 350);
        smallFont.draw(batch, "ENTER - Menu", 450, 250);
        smallFont.draw(batch, "ESC   - Exit", 450, 200);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.resetScore();
            game.setScreen(new MainMenuScreen(game));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        bigFont.dispose();
        smallFont.dispose();
    }
}
