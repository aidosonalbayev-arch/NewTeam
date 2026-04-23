package com.stealthassassin.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.stealthassassin.StealthAssassinGame;

public class MainMenuScreen implements Screen {

    private final StealthAssassinGame game;
    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;

    private final BitmapFont titleFont;
    private final BitmapFont subtitleFont;
    private final BitmapFont menuFont;
    private final BitmapFont smallFont;
    private final BitmapFont tagFont;

    private final Texture bgTexture;
    private final Texture shurikenTexture;
    private final Texture katanaTexture;
    private final Texture bloodTexture;
    private final Texture fogTexture;

    private float animTime = 0;
    private float titlePulse = 0;
    private float glitchTimer = 0;
    private float glitchOffset = 0;

    private final int STAR_COUNT = 80;
    private float[] starX = new float[STAR_COUNT];
    private float[] starY = new float[STAR_COUNT];
    private float[] starSpeed = new float[STAR_COUNT];
    private float[] starSize = new float[STAR_COUNT];
    private float[] starAlpha = new float[STAR_COUNT];

    private final int BLOOD_COUNT = 15;
    private float[] bloodX = new float[BLOOD_COUNT];
    private float[] bloodY = new float[BLOOD_COUNT];
    private float[] bloodAlpha = new float[BLOOD_COUNT];
    private float[] bloodSize = new float[BLOOD_COUNT];

    private float fogOffset1 = 0;
    private float fogOffset2 = 0;

    private int selectedOption = 0;
    private final String[] menuOptions = {"START GAME", "EXIT"};

    private float selectionGlow = 0;

    public MainMenuScreen(StealthAssassinGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        this.titleFont = new BitmapFont();
        this.titleFont.getData().setScale(5.5f);

        this.subtitleFont = new BitmapFont();
        this.subtitleFont.getData().setScale(1.5f);

        this.menuFont = new BitmapFont();
        this.menuFont.getData().setScale(2.5f);

        this.smallFont = new BitmapFont();
        this.smallFont.getData().setScale(1f);

        this.tagFont = new BitmapFont();
        this.tagFont.getData().setScale(0.9f);

        this.bgTexture = createGradientBackground();
        this.shurikenTexture = createShurikenTexture();
        this.katanaTexture = createKatanaTexture();
        this.bloodTexture = createBloodTexture();
        this.fogTexture = createFogTexture();

        for (int i = 0; i < STAR_COUNT; i++) {
            starX[i] = (float) (Math.random() * 1280);
            starY[i] = (float) (Math.random() * 720);
            starSpeed[i] = (float) (5 + Math.random() * 25);
            starSize[i] = (float) (0.8 + Math.random() * 2);
            starAlpha[i] = (float) (0.2 + Math.random() * 0.8);
        }

        for (int i = 0; i < BLOOD_COUNT; i++) {
            bloodX[i] = (float) (Math.random() * 1280);
            bloodY[i] = (float) (Math.random() * 200);
            bloodAlpha[i] = (float) (0.2 + Math.random() * 0.5);
            bloodSize[i] = (float) (3 + Math.random() * 8);
        }
    }

    private Texture createGradientBackground() {
        Pixmap pixmap = new Pixmap(1, 720, Pixmap.Format.RGBA8888);
        for (int y = 0; y < 720; y++) {
            float t = y / 720f;
            float r, g, b;
            if (t < 0.3f) {
                r = 0.05f;
                g = 0.02f;
                b = 0.1f;
            } else if (t < 0.7f) {
                float mid = (t - 0.3f) / 0.4f;
                r = 0.05f + mid * 0.15f;
                g = 0.02f;
                b = 0.1f - mid * 0.08f;
            } else {
                float low = (t - 0.7f) / 0.3f;
                r = 0.2f - low * 0.15f;
                g = 0.02f;
                b = 0.02f;
            }
            pixmap.setColor(r, g, b, 1);
            pixmap.drawPixel(0, y);
        }
        Texture tex = new Texture(pixmap);
        pixmap.dispose();
        return tex;
    }

    private Texture createShurikenTexture() {
        Pixmap pixmap = new Pixmap(80, 80, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.75f, 0.75f, 0.8f, 1);
        pixmap.fillTriangle(40, 0, 30, 40, 50, 40);
        pixmap.fillTriangle(40, 80, 30, 40, 50, 40);
        pixmap.fillTriangle(0, 40, 40, 30, 40, 50);
        pixmap.fillTriangle(80, 40, 40, 30, 40, 50);

        pixmap.setColor(0.4f, 0.4f, 0.45f, 1);
        pixmap.drawLine(40, 0, 30, 40);
        pixmap.drawLine(40, 80, 30, 40);
        pixmap.drawLine(0, 40, 40, 30);
        pixmap.drawLine(80, 40, 40, 30);

        pixmap.setColor(0.3f, 0.3f, 0.35f, 1);
        pixmap.fillCircle(40, 40, 8);
        pixmap.setColor(0.1f, 0.1f, 0.15f, 1);
        pixmap.fillCircle(40, 40, 4);

        Texture tex = new Texture(pixmap);
        pixmap.dispose();
        return tex;
    }

    private Texture createKatanaTexture() {
        Pixmap pixmap = new Pixmap(300, 40, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.85f, 0.85f, 0.9f, 1);
        pixmap.fillRectangle(40, 17, 240, 6);

        pixmap.fillTriangle(280, 14, 280, 26, 295, 20);

        pixmap.setColor(0.6f, 0.6f, 0.65f, 1);
        pixmap.drawLine(40, 20, 280, 20);

        pixmap.setColor(0.1f, 0.08f, 0.05f, 1);
        pixmap.fillRectangle(0, 15, 40, 10);

        pixmap.setColor(0.8f, 0.6f, 0.2f, 1);
        pixmap.fillRectangle(38, 13, 5, 14);
        pixmap.fillRectangle(0, 13, 3, 14);

        pixmap.setColor(0.3f, 0.25f, 0.1f, 1);
        for (int i = 5; i < 38; i += 6) {
            pixmap.drawLine(i, 15, i, 24);
        }

        Texture tex = new Texture(pixmap);
        pixmap.dispose();
        return tex;
    }

    private Texture createBloodTexture() {
        Pixmap pixmap = new Pixmap(16, 16, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.5f, 0.05f, 0.05f, 0.9f);
        pixmap.fillCircle(8, 8, 6);
        pixmap.setColor(0.7f, 0.1f, 0.1f, 0.7f);
        pixmap.fillCircle(8, 8, 4);

        Texture tex = new Texture(pixmap);
        pixmap.dispose();
        return tex;
    }

    private Texture createFogTexture() {
        Pixmap pixmap = new Pixmap(256, 128, Pixmap.Format.RGBA8888);

        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 128; y++) {
                float intensity = (float) (Math.sin(x * 0.03) * 0.5 + 0.5);
                intensity *= (float) (Math.cos(y * 0.05) * 0.3 + 0.7);
                intensity *= (float) (1 - y / 128.0);
                pixmap.setColor(0.5f, 0.5f, 0.6f, intensity * 0.3f);
                pixmap.drawPixel(x, y);
            }
        }

        Texture tex = new Texture(pixmap);
        tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
        pixmap.dispose();
        return tex;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        animTime += delta;
        titlePulse = (float) Math.sin(animTime * 2) * 0.08f + 1f;
        selectionGlow = (float) Math.sin(animTime * 4) * 0.3f + 0.7f;

        glitchTimer += delta;
        if (glitchTimer > 3f) {
            glitchTimer = 0;
            glitchOffset = (float) (Math.random() * 8 - 4);
        } else {
            glitchOffset *= 0.9f;
        }

        fogOffset1 += delta * 15;
        fogOffset2 -= delta * 10;

        for (int i = 0; i < STAR_COUNT; i++) {
            starY[i] -= starSpeed[i] * delta;
            if (starY[i] < 0) {
                starY[i] = 720;
                starX[i] = (float) (Math.random() * 1280);
            }
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(bgTexture, 0, 0, 1280, 720);
        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < STAR_COUNT; i++) {
            float twinkle = (float) Math.sin(animTime * 3 + i) * 0.3f + 0.7f;
            shapeRenderer.setColor(1f, 1f, 1f, starAlpha[i] * twinkle);
            shapeRenderer.circle(starX[i], starY[i], starSize[i]);
        }
        shapeRenderer.end();

        batch.begin();
        batch.setColor(0.5f, 0.3f, 0.4f, 0.4f);
        batch.draw(fogTexture, -fogOffset1 % 256 - 256, 0, 1536, 128);
        batch.draw(fogTexture, 1280 - fogOffset1 % 256, 0, 1536, 128);
        batch.setColor(0.3f, 0.2f, 0.3f, 0.3f);
        batch.draw(fogTexture, -fogOffset2 % 256, 40, 1536, 100);
        batch.setColor(Color.WHITE);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int x = 0; x < 1280; x += 8) {
            float waveHeight = (float) Math.sin(x * 0.02f + animTime * 0.5) * 12;
            shapeRenderer.setColor(0.03f, 0.01f, 0.05f, 1);
            shapeRenderer.rect(x, 0, 8, 100 + waveHeight);
        }
        shapeRenderer.end();

        batch.begin();
        batch.setColor(1, 1, 1, 0.12f);
        batch.draw(shurikenTexture,
            180 - 40, 480 - 40, 40, 40, 80, 80, 1.2f, 1.2f,
            animTime * 40, 0, 0, 80, 80, false, false);
        batch.draw(shurikenTexture,
            1100 - 40, 480 - 40, 40, 40, 80, 80, 1.2f, 1.2f,
            -animTime * 40, 0, 0, 80, 80, false, false);
        batch.draw(shurikenTexture,
            100 - 30, 200 - 30, 30, 30, 60, 60, 0.75f, 0.75f,
            -animTime * 60, 0, 0, 80, 80, false, false);
        batch.draw(shurikenTexture,
            1180 - 30, 200 - 30, 30, 30, 60, 60, 0.75f, 0.75f,
            animTime * 60, 0, 0, 80, 80, false, false);
        batch.setColor(Color.WHITE);
        batch.end();

        batch.begin();
        for (int i = 0; i < BLOOD_COUNT; i++) {
            batch.setColor(1, 1, 1, bloodAlpha[i]);
            batch.draw(bloodTexture,
                bloodX[i] - bloodSize[i], bloodY[i] - bloodSize[i],
                bloodSize[i] * 2, bloodSize[i] * 2);
        }
        batch.setColor(Color.WHITE);
        batch.end();

        batch.begin();
        batch.setColor(1, 1, 1, 0.85f);
        batch.draw(katanaTexture,
            120, 540, 150, 20, 300, 40, 1f, 1f,
            -5, 0, 0, 300, 40, false, false);
        batch.draw(katanaTexture,
            860, 540, 150, 20, 300, 40, 1f, 1f,
            5, 0, 0, 300, 40, true, false);
        batch.setColor(Color.WHITE);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.8f, 0.1f, 0.1f, 1f);
        shapeRenderer.rect(240, 655, 800, 4);
        shapeRenderer.setColor(0.5f, 0.05f, 0.05f, 1f);
        shapeRenderer.rect(280, 650, 720, 2);

        shapeRenderer.setColor(0.8f, 0.1f, 0.1f, 1f);
        shapeRenderer.rect(240, 500, 800, 4);
        shapeRenderer.setColor(0.5f, 0.05f, 0.05f, 1f);
        shapeRenderer.rect(280, 505, 720, 2);
        shapeRenderer.end();

        batch.begin();

        titleFont.getData().setScale(5.5f * titlePulse);
        titleFont.setColor(0, 0, 0, 0.9f);
        titleFont.draw(batch, "STEALTH ASSASSIN", 180 + glitchOffset, 618);
        titleFont.draw(batch, "STEALTH ASSASSIN", 182, 620);

        titleFont.setColor(0.95f, 0.15f, 0.15f, 1f);
        titleFont.draw(batch, "STEALTH ASSASSIN", 180, 625);

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.8f, 0.1f, 0.1f, 0.2f);
        shapeRenderer.rect(420, 200, 440, 220);

        shapeRenderer.setColor(0, 0, 0, 0.85f);
        shapeRenderer.rect(430, 210, 420, 200);

        shapeRenderer.setColor(0.5f, 0.05f, 0.05f, 0.95f);
        shapeRenderer.rect(430, 380, 420, 30);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glLineWidth(3);
        shapeRenderer.setColor(0.9f, 0.2f, 0.2f, 1f);
        shapeRenderer.rect(430, 210, 420, 200);
        Gdx.gl.glLineWidth(1);
        shapeRenderer.setColor(0.4f, 0.05f, 0.05f, 1f);
        shapeRenderer.rect(425, 205, 430, 210);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.9f, 0.2f, 0.2f, 1f);
        shapeRenderer.triangle(430, 410, 445, 410, 430, 395);
        shapeRenderer.triangle(850, 410, 835, 410, 850, 395);
        shapeRenderer.triangle(430, 210, 445, 210, 430, 225);
        shapeRenderer.triangle(850, 210, 835, 210, 850, 225);
        shapeRenderer.end();

        batch.begin();
        tagFont.setColor(0.95f, 0.85f, 0.3f, 1f);
        tagFont.draw(batch, "[ MAIN MENU ]", 590, 402);
        batch.end();

        batch.begin();
        for (int i = 0; i < menuOptions.length; i++) {
            float y = 340 - i * 80;
            boolean isSelected = (i == selectedOption);

            if (isSelected) {
                menuFont.getData().setScale(2.7f);

                menuFont.setColor(1f, 0.2f, 0.2f, selectionGlow * 0.5f);
                menuFont.draw(batch, ">  " + menuOptions[i] + "  <", 473, y + 3);

                menuFont.setColor(1f, 0.95f, 0.95f, 1f);
                menuFont.draw(batch, ">  " + menuOptions[i] + "  <", 470, y);
            } else {
                menuFont.getData().setScale(2.3f);
                menuFont.setColor(0.5f, 0.5f, 0.55f, 1f);
                float textOffset = menuOptions[i].equals("EXIT") ? 580 : 525;
                menuFont.draw(batch, menuOptions[i], textOffset, y);
            }
        }
        menuFont.getData().setScale(2.5f);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        float indicatorY = 340 - selectedOption * 80 - 10;
        shapeRenderer.setColor(0.9f, 0.2f, 0.2f, selectionGlow);
        shapeRenderer.rect(455, indicatorY - 5, 370, 3);
        shapeRenderer.end();

        batch.begin();
        smallFont.setColor(0.5f, 0.5f, 0.55f, 1f);
        smallFont.draw(batch,
            "[W/S] or [^/v]  NAVIGATE       [ENTER]  SELECT       [ESC]  QUIT",
            340, 130);

        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.3f, 0.05f, 0.05f, 0.8f);
        shapeRenderer.rect(320, 110, 640, 1);
        shapeRenderer.end();

        batch.begin();

        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.9f, 0.2f, 0.2f, 1f);
        shapeRenderer.circle(40, 695, 4);
        shapeRenderer.end();
        batch.begin();
        smallFont.setColor(0.8f, 0.8f, 0.85f, 1f);
        smallFont.draw(batch, "SA", 55, 700);

        batch.end();

        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W) ||
            Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectedOption = (selectedOption - 1 + menuOptions.length) % menuOptions.length;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S) ||
            Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedOption = (selectedOption + 1) % menuOptions.length;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            switch (selectedOption) {
                case 0: // START GAME
                    game.setScreen(new HeroSelectionScreen(game));
                    break;
                case 1: // EXIT
                    Gdx.app.exit();
                    break;
            }
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
        shapeRenderer.dispose();
        titleFont.dispose();
        subtitleFont.dispose();
        menuFont.dispose();
        smallFont.dispose();
        tagFont.dispose();
        bgTexture.dispose();
        shurikenTexture.dispose();
        katanaTexture.dispose();
        bloodTexture.dispose();
        fogTexture.dispose();
    }
}
