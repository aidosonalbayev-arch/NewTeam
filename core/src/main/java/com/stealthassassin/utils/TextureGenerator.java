package com.stealthassassin.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class TextureGenerator {


    public static Texture createNinjaTexture() {
        Pixmap pixmap = new Pixmap(40, 40, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.1f, 0.1f, 0.15f, 1);
        pixmap.fillRectangle(8, 10, 24, 25);

        pixmap.setColor(0.9f, 0.75f, 0.6f, 1);
        pixmap.fillCircle(20, 8, 6);

        pixmap.setColor(0, 0, 0, 1);
        pixmap.fillRectangle(14, 6, 12, 4);

        pixmap.setColor(1, 1, 1, 1);
        pixmap.fillRectangle(16, 6, 2, 2);
        pixmap.fillRectangle(22, 6, 2, 2);

        pixmap.setColor(0.7f, 0.7f, 0.7f, 1);
        pixmap.fillRectangle(32, 12, 3, 18);

        pixmap.setColor(0.1f, 0.1f, 0.15f, 1);
        pixmap.fillRectangle(10, 30, 8, 8);
        pixmap.fillRectangle(22, 30, 8, 8);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Texture createShadowTexture() {
        Pixmap pixmap = new Pixmap(40, 40, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.3f, 0.1f, 0.4f, 0.85f);
        pixmap.fillRectangle(8, 10, 24, 25);

        pixmap.setColor(0.15f, 0.05f, 0.25f, 1);
        pixmap.fillCircle(20, 10, 9);

        pixmap.setColor(0.5f, 1f, 1f, 1);
        pixmap.fillRectangle(16, 9, 2, 2);
        pixmap.fillRectangle(22, 9, 2, 2);

        pixmap.setColor(0.15f, 0.05f, 0.25f, 1);
        pixmap.fillRectangle(10, 30, 8, 8);
        pixmap.fillRectangle(22, 30, 8, 8);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Texture createRogueTexture() {
        Pixmap pixmap = new Pixmap(40, 40, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.5f, 0.3f, 0.2f, 1);
        pixmap.fillRectangle(6, 10, 28, 25);

        pixmap.setColor(0.9f, 0.75f, 0.6f, 1);
        pixmap.fillCircle(20, 8, 7);

        pixmap.setColor(0.3f, 0.15f, 0.05f, 1);
        pixmap.fillRectangle(14, 2, 12, 4);

        pixmap.setColor(0, 0, 0, 1);
        pixmap.fillRectangle(16, 7, 2, 2);
        pixmap.fillRectangle(22, 7, 2, 2);

        pixmap.setColor(0.6f, 0.6f, 0.6f, 1);
        pixmap.fillRectangle(2, 14, 5, 10);
        pixmap.fillRectangle(33, 14, 5, 10);

        pixmap.setColor(0.3f, 0.2f, 0.1f, 1);
        pixmap.fillRectangle(10, 30, 8, 8);
        pixmap.fillRectangle(22, 30, 8, 8);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }


    public static Texture createGuardTexture() {
        Pixmap pixmap = new Pixmap(40, 40, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.5f, 0.5f, 0.55f, 1);
        pixmap.fillRectangle(8, 10, 24, 25);

        pixmap.setColor(0.9f, 0.75f, 0.6f, 1);
        pixmap.fillCircle(20, 8, 6);

        pixmap.setColor(0.4f, 0.4f, 0.45f, 1);
        pixmap.fillRectangle(13, 2, 14, 6);

        pixmap.setColor(1, 0, 0, 1); // qyzyl kóz - jauyz
        pixmap.fillRectangle(16, 7, 2, 2);
        pixmap.fillRectangle(22, 7, 2, 2);

        pixmap.setColor(0.3f, 0.2f, 0.1f, 1);
        pixmap.fillRectangle(33, 5, 2, 25);
        pixmap.setColor(0.7f, 0.7f, 0.7f, 1);
        pixmap.fillTriangle(34, 0, 30, 8, 38, 8);

        pixmap.setColor(0.3f, 0.2f, 0.1f, 1);
        pixmap.fillRectangle(10, 30, 8, 8);
        pixmap.fillRectangle(22, 30, 8, 8);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Texture createArcherTexture() {
        Pixmap pixmap = new Pixmap(40, 40, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.2f, 0.5f, 0.2f, 1);
        pixmap.fillRectangle(8, 10, 24, 25);

        pixmap.setColor(0.9f, 0.75f, 0.6f, 1);
        pixmap.fillCircle(20, 8, 6);

        pixmap.setColor(0.15f, 0.35f, 0.15f, 1);
        pixmap.fillRectangle(13, 2, 14, 6);

        pixmap.setColor(1, 0, 0, 1);
        pixmap.fillRectangle(16, 7, 2, 2);
        pixmap.fillRectangle(22, 7, 2, 2);

        pixmap.setColor(0.4f, 0.25f, 0.1f, 1);
        pixmap.drawCircle(35, 20, 10);
        pixmap.drawCircle(35, 20, 11);

        pixmap.setColor(0.3f, 0.2f, 0.1f, 1);
        pixmap.fillRectangle(10, 30, 8, 8);
        pixmap.fillRectangle(22, 30, 8, 8);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Texture createKnightTexture() {
        Pixmap pixmap = new Pixmap(45, 45, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.2f, 0.2f, 0.25f, 1);
        pixmap.fillRectangle(8, 12, 29, 27);

        pixmap.setColor(0.15f, 0.15f, 0.2f, 1);
        pixmap.fillRectangle(12, 2, 21, 10);

        pixmap.setColor(1, 0.2f, 0, 1);
        pixmap.fillRectangle(17, 6, 3, 3);
        pixmap.fillRectangle(25, 6, 3, 3);

        pixmap.setColor(0.4f, 0.1f, 0.1f, 1);
        pixmap.fillCircle(7, 22, 7);

        pixmap.setColor(0.8f, 0.8f, 0.85f, 1);
        pixmap.fillRectangle(37, 10, 4, 22);

        pixmap.setColor(0.15f, 0.15f, 0.2f, 1);
        pixmap.fillRectangle(11, 34, 9, 10);
        pixmap.fillRectangle(25, 34, 9, 10);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Texture createMageTexture() {
        Pixmap pixmap = new Pixmap(40, 40, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.25f, 0.1f, 0.5f, 1);
        pixmap.fillTriangle(20, 8, 4, 38, 36, 38);

        pixmap.setColor(0.9f, 0.75f, 0.6f, 1);
        pixmap.fillCircle(20, 10, 5);

        pixmap.setColor(0.2f, 0.05f, 0.4f, 1);
        pixmap.fillTriangle(20, 0, 14, 8, 26, 8);

        pixmap.setColor(1f, 0.9f, 0.2f, 1);
        pixmap.fillRectangle(19, 4, 2, 2);

        pixmap.setColor(0f, 1f, 1f, 1);
        pixmap.fillRectangle(17, 9, 2, 2);
        pixmap.fillRectangle(21, 9, 2, 2);

        pixmap.setColor(0.4f, 0.25f, 0.1f, 1);
        pixmap.fillRectangle(34, 8, 3, 25);
        pixmap.setColor(0f, 1f, 1f, 1);
        pixmap.fillCircle(36, 7, 4);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Texture createBossTexture() {
        Pixmap pixmap = new Pixmap(70, 70, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.4f, 0.05f, 0.05f, 1);
        pixmap.fillRectangle(10, 15, 50, 45);

        pixmap.setColor(0.25f, 0.05f, 0.05f, 1);
        pixmap.fillRectangle(15, 2, 40, 15);

        pixmap.fillTriangle(15, 2, 5, -5, 15, 10);
        pixmap.fillTriangle(55, 2, 65, -5, 55, 10);

        pixmap.setColor(1f, 0.5f, 0f, 1);
        pixmap.fillRectangle(22, 8, 6, 4);
        pixmap.fillRectangle(42, 8, 6, 4);

        pixmap.setColor(0.9f, 0.9f, 0.9f, 1);
        pixmap.fillCircle(35, 35, 7);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fillRectangle(32, 33, 2, 2);
        pixmap.fillRectangle(37, 33, 2, 2);

        pixmap.setColor(0.7f, 0.7f, 0.75f, 1);
        pixmap.fillRectangle(0, 20, 10, 35);
        pixmap.fillRectangle(60, 20, 10, 35);

        pixmap.setColor(0.25f, 0.05f, 0.05f, 1);
        pixmap.fillRectangle(15, 55, 15, 15);
        pixmap.fillRectangle(40, 55, 15, 15);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }


    public static Texture createFloorTexture() {
        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.35f, 0.32f, 0.28f, 1);
        pixmap.fill();

        pixmap.setColor(0.2f, 0.18f, 0.15f, 1);
        pixmap.drawLine(0, 0, 64, 0);
        pixmap.drawLine(0, 32, 64, 32);
        pixmap.drawLine(0, 0, 0, 64);
        pixmap.drawLine(32, 0, 32, 64);

        pixmap.setColor(0.4f, 0.37f, 0.32f, 1);
        pixmap.fillRectangle(8, 8, 3, 3);
        pixmap.fillRectangle(50, 20, 4, 2);
        pixmap.fillRectangle(20, 45, 2, 3);
        pixmap.fillRectangle(40, 50, 3, 2);

        pixmap.setColor(0.25f, 0.22f, 0.18f, 1);
        pixmap.fillRectangle(15, 15, 2, 2);
        pixmap.fillRectangle(45, 8, 2, 2);
        pixmap.fillRectangle(10, 40, 2, 2);

        Texture texture = new Texture(pixmap);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        pixmap.dispose();
        return texture;
    }

    public static Texture createWallTexture() {
        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.4f, 0.25f, 0.15f, 1);
        pixmap.fill();

        pixmap.setColor(0.2f, 0.1f, 0.05f, 1);
        pixmap.drawLine(0, 16, 64, 16);
        pixmap.drawLine(0, 32, 64, 32);
        pixmap.drawLine(0, 48, 64, 48);

        pixmap.drawLine(16, 0, 16, 16);
        pixmap.drawLine(48, 0, 48, 16);
        pixmap.drawLine(32, 16, 32, 32);
        pixmap.drawLine(16, 32, 16, 48);
        pixmap.drawLine(48, 32, 48, 48);
        pixmap.drawLine(32, 48, 32, 64);

        Texture texture = new Texture(pixmap);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        pixmap.dispose();
        return texture;
    }

    public static Texture createWhitePixel() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
}
