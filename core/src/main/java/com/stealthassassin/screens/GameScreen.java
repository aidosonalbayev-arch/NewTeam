package com.stealthassassin.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.stealthassassin.StealthAssassinGame;
import com.stealthassassin.entities.enemies.Enemy;
import com.stealthassassin.entities.heroes.Hero;
import com.stealthassassin.facade.GameFacade;
import com.stealthassassin.factories.HeroFactory;
import com.stealthassassin.managers.DetectionManager;
import com.stealthassassin.managers.InputManager;
import com.stealthassassin.map.GameMap;
import com.stealthassassin.states.ChaseState;
import com.stealthassassin.utils.TextureGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameScreen implements Screen {

    private final StealthAssassinGame game;
    private final GameFacade gameFacade;
    private final InputManager inputManager;
    private final DetectionManager detectionManager;

    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;
    private final BitmapFont font;
    private final BitmapFont smallFont;

    private final GameMap gameMap;
    private final HeroFactory.HeroType heroType;
    private final Texture heroTexture;
    private final Map<String, Texture> enemyTextures;
    private final Texture whitePixel;

    private float attackEffectTimer = 0;
    private float hurtEffectTimer = 0;

    public GameScreen(StealthAssassinGame game, HeroFactory.HeroType heroType, String difficulty) {
        this.game = game;
        this.heroType = heroType;
        this.gameFacade = new GameFacade();
        this.inputManager = new InputManager();
        this.detectionManager = new DetectionManager();

        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        this.font = new BitmapFont();
        this.font.getData().setScale(1.3f);

        this.smallFont = new BitmapFont();
        this.smallFont.getData().setScale(1f);

        this.gameMap = new GameMap(1280, 720);

        switch (heroType) {
            case NINJA:  this.heroTexture = TextureGenerator.createNinjaTexture(); break;
            case SHADOW: this.heroTexture = TextureGenerator.createShadowTexture(); break;
            case ROGUE:  this.heroTexture = TextureGenerator.createRogueTexture(); break;
            default:     this.heroTexture = TextureGenerator.createNinjaTexture();
        }

        this.enemyTextures = new HashMap<>();
        enemyTextures.put("Guard",  TextureGenerator.createGuardTexture());
        enemyTextures.put("Archer", TextureGenerator.createArcherTexture());
        enemyTextures.put("Knight", TextureGenerator.createKnightTexture());
        enemyTextures.put("Mage",   TextureGenerator.createMageTexture());
        enemyTextures.put("Boss",   TextureGenerator.createBossTexture());

        this.whitePixel = TextureGenerator.createWhitePixel();

        gameFacade.startNewGame(heroType, difficulty);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Hero hero = gameFacade.getCurrentHero();
        List<Enemy> enemies = gameFacade.getCurrentLevel().getEnemies();

        hero.update(delta);

        inputManager.handleInput(hero, delta);
        clampHeroToMap(hero);

        detectionManager.checkDetection(hero, enemies);

        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.update(delta);
                clampEnemyToMap(enemy);

                float distanceToHero = enemy.getPosition().dst(hero.getPosition());
                if (distanceToHero < 60f) {
                    if (enemy.tryAttack(hero)) {
                        hurtEffectTimer = 0.4f;
                        System.out.println(enemy.getEnemyType() + " shabýyldady! -"
                            + (int) enemy.getDamage() + " HP. Qalǵany: "
                            + (int) hero.getHealth());
                    }
                }
            }
        }

        if (attackEffectTimer > 0) attackEffectTimer -= delta;
        if (hurtEffectTimer > 0) hurtEffectTimer -= delta;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        gameMap.render(batch);
        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) continue;
            boolean chasing = enemy.getState() instanceof ChaseState;
            if (chasing) {
                shapeRenderer.setColor(1f, 0.2f, 0.2f, 0.15f);
            } else {
                shapeRenderer.setColor(1f, 1f, 0.3f, 0.1f);
            }
            shapeRenderer.circle(
                enemy.getPosition().x,
                enemy.getPosition().y,
                enemy.getDetectionRadius()
            );
        }
        shapeRenderer.end();

        batch.begin();

        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) continue;

            Texture tex = enemyTextures.get(enemy.getEnemyType());
            float size = enemy.getEnemyType().equals("Boss") ? 70 : 40;

            batch.draw(tex,
                enemy.getPosition().x - size / 2,
                enemy.getPosition().y - size / 2,
                size, size);

            drawHealthBar(enemy.getPosition().x - size / 2,
                enemy.getPosition().y + size / 2 + 5,
                size, 5,
                enemy.getHealth() / getMaxEnemyHealth(enemy),
                Color.RED);
        }

        boolean invisible = hero.getStealthLevel() > 1.5f;
        batch.setColor(1, 1, 1, invisible ? 0.4f : 1f);
        batch.draw(heroTexture,
            hero.getPosition().x - 20,
            hero.getPosition().y - 20,
            40, 40);
        batch.setColor(Color.WHITE);

        batch.end();

        if (hurtEffectTimer > 0) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(1f, 0f, 0f, hurtEffectTimer * 0.6f);
            shapeRenderer.rect(0, 0, 1280, 720);
            shapeRenderer.end();
        }

        if (attackEffectTimer > 0) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(0.3f, 1f, 0.3f, attackEffectTimer * 2f);
            shapeRenderer.circle(hero.getPosition().x, hero.getPosition().y, 60);
            shapeRenderer.end();
        }

        batch.begin();
        drawUI(hero, enemies);
        batch.end();

        handleInputs(hero, enemies);
        checkGameOver(hero, enemies);
    }

    private void drawHealthBar(float x, float y, float width, float height,
                               float percent, Color color) {
        batch.setColor(0.2f, 0.2f, 0.2f, 1);
        batch.draw(whitePixel, x, y, width, height);
        batch.setColor(color);
        batch.draw(whitePixel, x, y, width * Math.max(0, Math.min(1, percent)), height);
        batch.setColor(Color.WHITE);
    }

    private float getMaxEnemyHealth(Enemy enemy) {
        switch (enemy.getEnemyType()) {
            case "Guard":  return 50f;
            case "Archer": return 40f;
            case "Knight": return 120f;
            case "Mage":   return 60f;
            case "Boss":   return 500f;
            default:       return 100f;
        }
    }

    private void drawUI(Hero hero, List<Enemy> enemies) {
        font.setColor(Color.WHITE);
        font.draw(batch, "Hero: " + hero.getHeroName(), 50, 700);

        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1);
        shapeRenderer.rect(50, 655, 250, 22);

        float hpPercent = hero.getHealth() / hero.getMaxHealth();
        if (hpPercent > 0.6f) {
            shapeRenderer.setColor(0.2f, 0.9f, 0.3f, 1f);
        } else if (hpPercent > 0.3f) {
            shapeRenderer.setColor(0.95f, 0.75f, 0.15f, 1f);
        } else {
            shapeRenderer.setColor(0.9f, 0.2f, 0.2f, 1f);
        }
        shapeRenderer.rect(50, 655, 250 * Math.max(0, Math.min(1, hpPercent)), 22);

        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0.6f, 0.6f, 0.6f, 1f);
        shapeRenderer.rect(50, 655, 250, 22);
        shapeRenderer.end();

        batch.begin();

        font.setColor(Color.WHITE);
        font.draw(batch, "HP: " + (int) hero.getHealth() + " / " + (int) hero.getMaxHealth(),
            60, 674);

        font.setColor(Color.YELLOW);
        font.draw(batch, "Score: " + game.getPlayerScore(), 50, 625);

        long aliveEnemies = enemies.stream().filter(Enemy::isAlive).count();
        font.setColor(Color.RED);
        font.draw(batch, "Enemies: " + aliveEnemies + " / " + enemies.size(), 50, 595);

        if (hero.getStealthLevel() > 1.5f) {
            font.setColor(0.7f, 0.3f, 0.9f, 1f);
            font.draw(batch, "[INVISIBILITY ENABLED]", 50, 565);
        }

        if (hero.getHeroName().equals("Ninja") && hero.getDamage() > 30) {
            font.setColor(1f, 0.4f, 0.2f, 1f);
            font.draw(batch, "[SHADOW STRIKE ENABLED]", 50, 565);
        }

        smallFont.setColor(Color.LIGHT_GRAY);
        smallFont.draw(batch,
            "[WASD] Move  |  [SPACE] Ability  |  [F] Atack  |  [ESC] Menu",
            50, 40);

        font.setColor(0.8f, 0.8f, 0.8f, 1f);
        font.draw(batch, "Level: " + gameFacade.getCurrentLevel().getLevelName(), 950, 700);
    }

    private void handleInputs(Hero hero, List<Enemy> enemies) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            Enemy closestEnemy = null;
            float closestDistance = 60f;

            for (Enemy enemy : enemies) {
                if (!enemy.isAlive()) continue;
                float distance = enemy.getPosition().dst(hero.getPosition());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestEnemy = enemy;
                }
            }

            if (closestEnemy != null) {
                boolean wasUndetected = !(closestEnemy.getState() instanceof ChaseState);
                closestEnemy.takeDamage(hero.getDamage());
                attackEffectTimer = 0.3f;

                if (!closestEnemy.isAlive()) {
                    gameFacade.onEnemyKilled(closestEnemy);
                    if (wasUndetected) {
                        gameFacade.onStealthKill();
                    }
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    private void clampHeroToMap(Hero hero) {
        int wall = gameMap.getWallThickness();
        if (hero.getPosition().x < wall + 20) hero.getPosition().x = wall + 20;
        if (hero.getPosition().x > gameMap.getMapWidth() - wall - 20)
            hero.getPosition().x = gameMap.getMapWidth() - wall - 20;
        if (hero.getPosition().y < wall + 20) hero.getPosition().y = wall + 20;
        if (hero.getPosition().y > gameMap.getMapHeight() - wall - 20)
            hero.getPosition().y = gameMap.getMapHeight() - wall - 20;
    }

    private void clampEnemyToMap(Enemy enemy) {
        int wall = gameMap.getWallThickness();
        if (enemy.getPosition().x < wall + 20) enemy.getPosition().x = wall + 20;
        if (enemy.getPosition().x > gameMap.getMapWidth() - wall - 20)
            enemy.getPosition().x = gameMap.getMapWidth() - wall - 20;
        if (enemy.getPosition().y < wall + 20) enemy.getPosition().y = wall + 20;
        if (enemy.getPosition().y > gameMap.getMapHeight() - wall - 20)
            enemy.getPosition().y = gameMap.getMapHeight() - wall - 20;
    }

    private void checkGameOver(Hero hero, List<Enemy> enemies) {
        boolean allDead = enemies.stream().noneMatch(Enemy::isAlive);
        if (allDead) {
            game.setScreen(new GameOverScreen(game, true));
            return;
        }
        if (!hero.isAlive()) {
            game.setScreen(new GameOverScreen(game, false));
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
        font.dispose();
        smallFont.dispose();
        gameMap.dispose();
        heroTexture.dispose();
        for (Texture t : enemyTextures.values()) {
            t.dispose();
        }
        whitePixel.dispose();
    }
}
