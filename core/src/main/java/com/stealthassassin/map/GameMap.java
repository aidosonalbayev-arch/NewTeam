package com.stealthassassin.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stealthassassin.utils.TextureGenerator;

public class GameMap {

    private final Texture floorTexture;
    private final Texture wallTexture;
    private final int mapWidth;
    private final int mapHeight;
    private final int wallThickness = 40;

    public GameMap(int width, int height) {
        this.mapWidth = width;
        this.mapHeight = height;
        this.floorTexture = TextureGenerator.createFloorTexture();
        this.wallTexture = TextureGenerator.createWallTexture();
    }

    public void render(SpriteBatch batch) {
        batch.draw(floorTexture, 0, 0, mapWidth, mapHeight,
            0, 0, mapWidth / 64, mapHeight / 64);

        batch.draw(wallTexture, 0, 0, mapWidth, wallThickness,
            0, 0, mapWidth / 64, 1);

        batch.draw(wallTexture, 0, mapHeight - wallThickness, mapWidth, wallThickness,
            0, 0, mapWidth / 64, 1);

        batch.draw(wallTexture, 0, 0, wallThickness, mapHeight,
            0, 0, 1, mapHeight / 64);

        batch.draw(wallTexture, mapWidth - wallThickness, 0, wallThickness, mapHeight,
            0, 0, 1, mapHeight / 64);


        batch.draw(wallTexture, 300, 200, 100, 40);
        batch.draw(wallTexture, 700, 400, 40, 100);
        batch.draw(wallTexture, 1000, 250, 80, 80);
    }

    public int getMapWidth() { return mapWidth; }
    public int getMapHeight() { return mapHeight; }
    public int getWallThickness() { return wallThickness; }

    public void dispose() {
        floorTexture.dispose();
        wallTexture.dispose();
    }
}
