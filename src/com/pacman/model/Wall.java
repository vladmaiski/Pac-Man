package com.pacman.model;

import com.pacman.service.GameObject;

import java.awt.*;

public class Wall extends Rectangle {

    private Texture wallTexture;

    public Wall(int x, int y) {
        setBounds(x, y, Level.BLOCK_SIZE, Level.BLOCK_SIZE);
        wallTexture = new Texture(GameObject.WALL);
    }


    public void render(Graphics graphics) {
        graphics.drawImage(wallTexture.getSprite(null, 0), x, y, 32, 32, null);
    }

}
