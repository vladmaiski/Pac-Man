package com.pacman.model;

import com.pacman.service.GameObject;
import com.pacman.service.HeroService;

import java.awt.*;

public class Hero extends HeroService {

    private Texture heroTexture;

    public Hero(int x, int y) {
        startX = x;
        startY = y;
        setBounds(x, y, Level.BLOCK_SIZE, Level.BLOCK_SIZE);
        heroTexture = new Texture(GameObject.PACMAN);
        speed = 4;
    }

    public void render(Graphics graphics) {
        graphics.drawImage((heroTexture.getSprite(currentDirection, imageIndex % 2)), x, y, 32, 32, null);
    }

}
