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
import com.stealthassassin.factories.HeroFactory;
import com.stealthassassin.utils.TextureGenerator;

public class HeroSelectionScreen implements Screen {

    private final StealthAssassinGame game;
    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;

    private final BitmapFont titleFont;
    private final BitmapFont nameFont;
    private final BitmapFont statFont;
    private final BitmapFont descFont;
    private final BitmapFont smallFont;
    private final BitmapFont tagFont;

    private final Texture bgTexture;
    private final Texture ninjaTexture;
    private final Texture shadowTexture;
    private final Texture rogueTexture;
    private final Texture fogTexture;
    private final Texture shurikenTexture;

    private int selectedHero = 0;
    private float animTime = 0;
    private float selectionGlow = 0;
    private String selectedDifficulty = "EASY";

    private final int STAR_COUNT = 60;
    private float[] starX = new float[STAR_COUNT];
    private float[] starY = new float[STAR_COUNT];
    private float[] starSpeed = new float[STAR_COUNT];
    private float[] starAlpha = new float[STAR_COUNT];

    private float fogOffset1 = 0;
    private float fogOffset2 = 0;

    private final String[] heroNames = {"NINJA", "SHADOW", "ROGUE"};
    private final String[] heroDescs = {
        "Fast and stealthy warrior.\nShadow Strike ability - damage x2 (5 s).",
        "The most hidden hero.\nInvisibility ability - enemies cannot see (5 s).",
        "Strong and powerful warrior.\nIron Skin ability - +50 max HP."
    };
    private final int[] heroHP = {80, 60, 120};
    private final int[] heroSpeed = {200, 180, 150};
    private final float[] heroStealth = {0.9f, 1.0f, 0.7f};
    private final int[] heroDamage = {30, 25, 45};
    private final Color[] heroColors = {
        new Color(0.2f, 0.7f, 1f, 1f),
        new Color(0.8f, 0.3f, 1f, 1f),
        new Color(1f, 0.3f, 0.2f, 1f)
    };

    public HeroSelectionScreen(StealthAssassinGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        this.titleFont = new BitmapFont();
        this.titleFont.getData().setScale(3.5f);

        this.nameFont = new BitmapFont();
        this.nameFont.getData().setScale(2.2f);

        this.statFont = new BitmapFont();
        this.statFont.getData().setScale(1.2f);

        this.descFont = new BitmapFont();
        this.descFont.getData().setScale(1.1f);

        this.smallFont = new BitmapFont();
        this.smallFont.getData().setScale(1f);

        this.tagFont = new BitmapFont();
        this.tagFont.getData().setScale(0.9f);

        this.bgTexture = createGradientBackground();
        this.ninjaTexture = TextureGenerator.createNinjaTexture();
        this.shadowTexture = TextureGenerator.createShadowTexture();
        this.rogueTexture = TextureGenerator.createRogueTexture();
        this.fogTexture = createFogTexture();
        this.shurikenTexture = createShurikenTexture();

        for (int i = 0; i < STAR_COUNT; i++) {
            starX[i] = (float) (Math.random() * 1280);
            starY[i] = (float) (Math.random() * 720);
            starSpeed[i] = (float) (3 + Math.random() * 15);
            starAlpha[i] = (float) (0.2 + Math.random() * 0.6);
        }
    }

    private Texture createGradientBackground() {
        Pixmap pixmap = new Pixmap(1, 720, Pixmap.Format.RGBA8888);
        for (int y = 0; y < 720; y++) {
            float t = y / 720f;
            float r, g, b;
            if (t < 0.3f) {
                r = 0.08f;
                g = 0.03f;
                b = 0.1f;
            } else if (t < 0.7f) {
                float mid = (t - 0.3f) / 0.4f;
                r = 0.08f + mid * 0.12f;
                g = 0.03f;
                b = 0.1f - mid * 0.08f;
            } else {
                float low = (t - 0.7f) / 0.3f;
                r = 0.2f - low * 0.15f;
                g = 0.03f;
                b = 0.02f;
            }
            pixmap.setColor(r, g, b, 1);
            pixmap.drawPixel(0, y);
        }
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
                pixmap.setColor(0.5f, 0.5f, 0.6f, intensity * 0.25f);
                pixmap.drawPixel(x, y);
            }
        }
        Texture tex = new Texture(pixmap);
        tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
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
        pixmap.setColor(0.3f, 0.3f, 0.35f, 1);
        pixmap.fillCircle(40, 40, 8);
        pixmap.setColor(0.1f, 0.1f, 0.15f, 1);
        pixmap.fillCircle(40, 40, 4);
        Texture tex = new Texture(pixmap);
        pixmap.dispose();
        return tex;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        animTime += delta;
        selectionGlow = (float) Math.sin(animTime * 4) * 0.3f + 0.7f;

        for (int i = 0; i < STAR_COUNT; i++) {
            starY[i] -= starSpeed[i] * delta;
            if (starY[i] < 0) {
                starY[i] = 720;
                starX[i] = (float) (Math.random() * 1280);
            }
        }

        fogOffset1 += delta * 12;
        fogOffset2 -= delta * 8;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(bgTexture, 0, 0, 1280, 720);
        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < STAR_COUNT; i++) {
            float twinkle = (float) Math.sin(animTime * 2 + i) * 0.3f + 0.7f;
            shapeRenderer.setColor(1f, 1f, 1f, starAlpha[i] * twinkle * 0.5f);
            shapeRenderer.circle(starX[i], starY[i], 1.5f);
        }
        shapeRenderer.end();

        batch.begin();
        batch.setColor(0.5f, 0.3f, 0.4f, 0.35f);
        batch.draw(fogTexture, -fogOffset1 % 256 - 256, 0, 1536, 128);
        batch.draw(fogTexture, 1280 - fogOffset1 % 256, 0, 1536, 128);
        batch.setColor(0.3f, 0.2f, 0.3f, 0.25f);
        batch.draw(fogTexture, -fogOffset2 % 256, 50, 1536, 100);
        batch.setColor(Color.WHITE);
        batch.end();

        batch.begin();
        batch.setColor(1, 1, 1, 0.08f);
        batch.draw(shurikenTexture,
            80 - 30, 620 - 30, 30, 30, 60, 60, 1f, 1f,
            animTime * 30, 0, 0, 80, 80, false, false);
        batch.draw(shurikenTexture,
            1200 - 30, 620 - 30, 30, 30, 60, 60, 1f, 1f,
            -animTime * 30, 0, 0, 80, 80, false, false);
        batch.setColor(Color.WHITE);
        batch.end();

        batch.begin();
        // Kóleńke
        titleFont.setColor(0, 0, 0, 0.9f);
        titleFont.draw(batch, "CHOOSE YOUR ASSASSIN", 278, 658);
        // Qyzyl glıtch
        titleFont.setColor(1f, 0.1f, 0.1f, 0.4f);
        titleFont.draw(batch, "CHOOSE YOUR ASSASSIN", 275, 663);
        // Negızgı
        titleFont.setColor(0.95f, 0.95f, 0.95f, 1f);
        titleFont.draw(batch, "CHOOSE YOUR ASSASSIN", 280, 660);

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.8f, 0.1f, 0.1f, 1f);
        shapeRenderer.rect(250, 600, 780, 3);
        shapeRenderer.setColor(0.4f, 0.05f, 0.05f, 1f);
        shapeRenderer.rect(290, 595, 700, 2);
        shapeRenderer.end();

        for (int i = 0; i < 3; i++) {
            drawHeroCard(i, 130 + i * 360, 200, i == selectedHero);
        }

        Color heroColor = heroColors[selectedHero];

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 0.85f);
        shapeRenderer.rect(100, 60, 1080, 100);
        shapeRenderer.setColor(heroColor.r, heroColor.g, heroColor.b, 1f);
        shapeRenderer.rect(100, 60, 6, 100);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glLineWidth(2);
        shapeRenderer.setColor(heroColor.r, heroColor.g, heroColor.b, 0.8f);
        shapeRenderer.rect(100, 60, 1080, 100);
        Gdx.gl.glLineWidth(1);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(heroColor.r, heroColor.g, heroColor.b, 1f);
        shapeRenderer.triangle(100, 160, 115, 160, 100, 145);
        shapeRenderer.triangle(1180, 160, 1165, 160, 1180, 145);
        shapeRenderer.triangle(100, 60, 115, 60, 100, 75);
        shapeRenderer.triangle(1180, 60, 1165, 60, 1180, 75);
        shapeRenderer.end();

        batch.begin();
        descFont.setColor(heroColor);
        descFont.getData().setScale(1.6f);
        descFont.draw(batch, heroNames[selectedHero], 140, 140);
        descFont.getData().setScale(1.15f);
        descFont.setColor(0.9f, 0.9f, 0.95f, 1f);
        descFont.draw(batch, heroDescs[selectedHero], 140, 105);

        smallFont.setColor(0.6f, 0.6f, 0.65f, 1f);
        smallFont.draw(batch, "DIFFICULTY:", 830, 135);

        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (selectedDifficulty.equals("EASY")) {
            shapeRenderer.setColor(0.2f, 0.7f, 0.2f, 0.8f);
            shapeRenderer.rect(830, 95, 80, 30);
        } else {
            shapeRenderer.setColor(0.15f, 0.15f, 0.18f, 0.8f);
            shapeRenderer.rect(830, 95, 80, 30);
        }
        if (selectedDifficulty.equals("HARD")) {
            shapeRenderer.setColor(0.9f, 0.2f, 0.2f, 0.8f);
            shapeRenderer.rect(930, 95, 80, 30);
        } else {
            shapeRenderer.setColor(0.15f, 0.15f, 0.18f, 0.8f);
            shapeRenderer.rect(930, 95, 80, 30);
        }
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0.3f, 0.9f, 0.3f, 1f);
        shapeRenderer.rect(830, 95, 80, 30);
        shapeRenderer.setColor(0.9f, 0.3f, 0.3f, 1f);
        shapeRenderer.rect(930, 95, 80, 30);
        shapeRenderer.end();

        batch.begin();
        statFont.setColor(Color.WHITE);
        statFont.draw(batch, "[E] EASY", 842, 115);
        statFont.draw(batch, "[H] HARD", 942, 115);

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(heroColor.r * 0.3f, heroColor.g * 0.3f, heroColor.b * 0.3f, selectionGlow);
        shapeRenderer.rect(1030, 75, 140, 70);
        shapeRenderer.setColor(0, 0, 0, 0.9f);
        shapeRenderer.rect(1035, 80, 130, 60);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glLineWidth(2);
        shapeRenderer.setColor(heroColor.r, heroColor.g, heroColor.b, 1f);
        shapeRenderer.rect(1035, 80, 130, 60);
        Gdx.gl.glLineWidth(1);
        shapeRenderer.end();

        batch.begin();
        descFont.setColor(heroColor);
        descFont.getData().setScale(1.3f);
        descFont.draw(batch, "[ENTER]", 1058, 123);
        descFont.getData().setScale(1f);
        descFont.setColor(Color.WHITE);
        descFont.draw(batch, "START MISSION", 1050, 98);

        smallFont.setColor(0.5f, 0.5f, 0.55f, 1f);
        smallFont.draw(batch,
            "[A/D] or [<-/->]  SELECT HERO       [E/H]  DIFFICULTY       [ENTER]  CONFIRM       [ESC]  BACK",
            175, 30);

        batch.end();

        handleInput();
    }

    private void drawHeroCard(int index, float x, float y, boolean selected) {
        Color cardColor = heroColors[index];

        float cardY = y;
        float cardScale = 1f;
        if (selected) {
            cardY += Math.sin(animTime * 3) * 6;
            cardScale = 1.06f;
        }

        float cardW = 300 * cardScale;
        float cardH = 380 * cardScale;
        float cardX = x - (cardW - 300) / 2;

        if (selected) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            for (int i = 6; i > 0; i--) {
                shapeRenderer.setColor(cardColor.r, cardColor.g, cardColor.b, 0.07f * selectionGlow);
                shapeRenderer.rect(cardX - i * 4, cardY - i * 4,
                    cardW + i * 8, cardH + i * 8);
            }
            shapeRenderer.end();
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.05f, 0.05f, 0.08f, 0.95f);
        shapeRenderer.rect(cardX, cardY, cardW, cardH);

        shapeRenderer.setColor(cardColor.r * 0.35f, cardColor.g * 0.35f, cardColor.b * 0.35f, 1f);
        shapeRenderer.rect(cardX, cardY + cardH - 55, cardW, 55);

        shapeRenderer.setColor(cardColor.r * 0.5f, cardColor.g * 0.5f, cardColor.b * 0.5f, 0.6f);
        shapeRenderer.rect(cardX + 20, cardY + 15, cardW - 40, 2);
        shapeRenderer.rect(cardX + 20, cardY + 10, cardW - 40, 1);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glLineWidth(selected ? 3 : 1);
        if (selected) {
            shapeRenderer.setColor(cardColor.r, cardColor.g, cardColor.b, 1f);
        } else {
            shapeRenderer.setColor(cardColor.r * 0.5f, cardColor.g * 0.5f, cardColor.b * 0.5f, 0.7f);
        }
        shapeRenderer.rect(cardX, cardY, cardW, cardH);
        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);

        if (selected) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(cardColor.r, cardColor.g, cardColor.b, 1f);
            // 4 múıyste
            shapeRenderer.triangle(cardX, cardY, cardX + 15, cardY, cardX, cardY + 15);
            shapeRenderer.triangle(cardX + cardW, cardY, cardX + cardW - 15, cardY, cardX + cardW, cardY + 15);
            shapeRenderer.triangle(cardX, cardY + cardH, cardX + 15, cardY + cardH, cardX, cardY + cardH - 15);
            shapeRenderer.triangle(cardX + cardW, cardY + cardH, cardX + cardW - 15, cardY + cardH, cardX + cardW, cardY + cardH - 15);
            shapeRenderer.end();
        }

        batch.begin();

        // Japon tag (ústınde)
        tagFont.setColor(cardColor.r * 1.2f, cardColor.g * 1.2f, cardColor.b * 1.2f, 0.9f);
        tagFont.getData().setScale(1.3f);
        tagFont.getData().setScale(0.9f);

        // Qaharman aty (negızgı)
        nameFont.setColor(Color.WHITE);
        nameFont.getData().setScale(selected ? 2.3f : 2.0f);
        String name = heroNames[index];
        float nameWidth = name.length() * 21;
        nameFont.draw(batch, name, cardX + (cardW - nameWidth) / 2, cardY + cardH - 32);
        nameFont.getData().setScale(2.2f);

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(cardColor.r * 0.4f, cardColor.g * 0.4f, cardColor.b * 0.4f, 1f);
        shapeRenderer.circle(cardX + cardW / 2, cardY + cardH - 145, 72);
        shapeRenderer.setColor(0.03f, 0.03f, 0.05f, 1f);
        shapeRenderer.circle(cardX + cardW / 2, cardY + cardH - 145, 65);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glLineWidth(2);
        shapeRenderer.setColor(cardColor.r, cardColor.g, cardColor.b, 1f);
        shapeRenderer.circle(cardX + cardW / 2, cardY + cardH - 145, 70);
        Gdx.gl.glLineWidth(1);
        shapeRenderer.end();

        batch.begin();
        Texture heroTex;
        switch (index) {
            case 0:  heroTex = ninjaTexture; break;
            case 1:  heroTex = shadowTexture; break;
            default: heroTex = rogueTexture;
        }
        float texSize = selected ? 105 : 95;
        batch.draw(heroTex,
            cardX + cardW / 2 - texSize / 2,
            cardY + cardH - 145 - texSize / 2,
            texSize, texSize);
        batch.end();

        float statY = cardY + cardH - 245;

        batch.begin();
        statFont.getData().setScale(1.1f);

        statFont.setColor(0.8f, 0.8f, 0.85f, 1f);
        statFont.draw(batch, "HP", cardX + 30, statY);
        statFont.setColor(Color.WHITE);
        statFont.draw(batch, String.valueOf(heroHP[index]), cardX + cardW - 55, statY);
        batch.end();
        drawStatBar(cardX + 30, statY - 12, cardW - 60, 5,
            heroHP[index] / 150f,
            new Color(0.3f, 0.9f, 0.3f, 1f));

        batch.begin();
        statFont.setColor(0.8f, 0.8f, 0.85f, 1f);
        statFont.draw(batch, "SPEED", cardX + 30, statY - 28);
        statFont.setColor(Color.WHITE);
        statFont.draw(batch, String.valueOf(heroSpeed[index]), cardX + cardW - 65, statY - 28);
        batch.end();
        drawStatBar(cardX + 30, statY - 40, cardW - 60, 5,
            heroSpeed[index] / 250f,
            new Color(0.3f, 0.7f, 0.9f, 1f));

        batch.begin();
        statFont.setColor(0.8f, 0.8f, 0.85f, 1f);
        statFont.draw(batch, "STEALTH", cardX + 30, statY - 56);
        statFont.setColor(Color.WHITE);
        statFont.draw(batch, String.format("%.1f", heroStealth[index]), cardX + cardW - 55, statY - 56);
        batch.end();
        drawStatBar(cardX + 30, statY - 68, cardW - 60, 5,
            heroStealth[index] / 1.2f,
            new Color(0.8f, 0.4f, 0.9f, 1f));

        batch.begin();
        statFont.setColor(0.8f, 0.8f, 0.85f, 1f);
        statFont.draw(batch, "DAMAGE", cardX + 30, statY - 84);
        statFont.setColor(Color.WHITE);
        statFont.draw(batch, String.valueOf(heroDamage[index]), cardX + cardW - 55, statY - 84);
        batch.end();
        drawStatBar(cardX + 30, statY - 96, cardW - 60, 5,
            heroDamage[index] / 50f,
            new Color(0.9f, 0.4f, 0.3f, 1f));

        statFont.getData().setScale(1.2f);

        if (selected) {
            batch.begin();
            statFont.setColor(cardColor);
            statFont.getData().setScale(1.4f);
            String selectText = "SELECTED";
            float selectWidth = selectText.length() * 11;
            statFont.draw(batch, selectText, cardX + (cardW - selectWidth) / 2, cardY + 30);
            statFont.getData().setScale(1.2f);
            batch.end();
        } else {
            batch.begin();
            smallFont.setColor(0.4f, 0.4f, 0.45f, 1f);
            String pressText = "[ " + (index + 1) + " ]";
            float pressWidth = pressText.length() * 8;
            smallFont.draw(batch, pressText, cardX + (cardW - pressWidth) / 2, cardY + 30);
            batch.end();
        }
    }

    private void drawStatBar(float x, float y, float width, float height,
                             float percent, Color color) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.15f, 0.15f, 0.18f, 1f);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, width * Math.min(1, percent), height);
        shapeRenderer.setColor(color.r * 1.3f, color.g * 1.3f, color.b * 1.3f, 0.8f);
        shapeRenderer.rect(x, y + height - 1, width * Math.min(1, percent), 1);
        shapeRenderer.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.A) ||
            Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            selectedHero = (selectedHero - 1 + 3) % 3;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D) ||
            Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            selectedHero = (selectedHero + 1) % 3;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            selectedDifficulty = "EASY";
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            selectedDifficulty = "HARD";
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) selectedHero = 0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) selectedHero = 1;
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) selectedHero = 2;

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            HeroFactory.HeroType heroType;
            switch (selectedHero) {
                case 0:  heroType = HeroFactory.HeroType.NINJA; break;
                case 1:  heroType = HeroFactory.HeroType.SHADOW; break;
                default: heroType = HeroFactory.HeroType.ROGUE;
            }
            game.setScreen(new GameScreen(game, heroType, selectedDifficulty));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
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
        nameFont.dispose();
        statFont.dispose();
        descFont.dispose();
        smallFont.dispose();
        tagFont.dispose();
        bgTexture.dispose();
        ninjaTexture.dispose();
        shadowTexture.dispose();
        rogueTexture.dispose();
        fogTexture.dispose();
        shurikenTexture.dispose();
    }
}
