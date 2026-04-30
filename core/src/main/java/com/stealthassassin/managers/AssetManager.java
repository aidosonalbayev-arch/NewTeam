package com.stealthassassin.managers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetManager {

    // ==================== SINGLETON ====================

    private static AssetManager instance;

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    // ==================== CACHE ====================

    private final Map<String, Texture> textures;
    private final Map<String, BitmapFont> fonts;

    private AssetManager() {
        textures = new HashMap<>();
        fonts = new HashMap<>();
    }

    // ==================== TEXTURA ====================
    public Texture getTexture(String path) {
        if (!textures.containsKey(path)) {
            try {
                Texture texture = new Texture(Gdx.files.internal(path));
                textures.put(path, texture);
                Gdx.app.log("AssetManager", "Tekstýra júktelý: " + path);
            } catch (Exception e) {
                Gdx.app.error("AssetManager", "Tekstýra júkteý qatesı: " + path, e);
                return null;
            }
        }
        return textures.get(path);
    }

    public void addTexture(String key, Texture texture) {
        textures.put(key, texture);
    }

    // ==================== FONST ====================

    public BitmapFont getFont(String path) {
        if (!fonts.containsKey(path)) {
            try {
                BitmapFont font = new BitmapFont(Gdx.files.internal(path));
                fonts.put(path, font);
                Gdx.app.log("AssetManager", "Qarip júktelý: " + path);
            } catch (Exception e) {
                Gdx.app.error("AssetManager", "Qarip júkteý qatesı: " + path, e);
                return null;
            }
        }
        return fonts.get(path);
    }

    public BitmapFont getDefaultFont() {
        if (!fonts.containsKey("default")) {
            fonts.put("default", new BitmapFont());
        }
        return fonts.get("default");
    }

    // ==================== TAZALAÝ ====================

    public void dispose() {
        Gdx.app.log("AssetManager", "Resýrstar tazalanýda...");

        for (Texture texture : textures.values()) {
            texture.dispose();
        }
        textures.clear();

        for (BitmapFont font : fonts.values()) {
            font.dispose();
        }
        fonts.clear();

        Gdx.app.log("AssetManager", "Barlyq resýrstar tazalandy.");
    }

    // ==================== STATISTIKA ====================

    public int getLoadedAssetsCount() {
        return textures.size() + fonts.size();
    }

    public void printStats() {
        Gdx.app.log("AssetManager",
            "Tekstýralar: " + textures.size() +
            ", Qarıpter: " + fonts.size());
    }
}
