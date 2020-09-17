package com.pacman.model;

import com.pacman.service.Direction;
import com.pacman.service.GameObject;
import com.pacman.service.RandomGhostService;

import java.awt.*;

public class RandomGhost extends RandomGhostService {

    private Texture randomGhostTexture;

    public RandomGhost(int x, int y) {
        startX = x;
        startY = y;
        setBounds(x, y, Level.BLOCK_SIZE, Level.BLOCK_SIZE);
        randomGhostTexture = new Texture(GameObject.RANDOM_GHOST);
        direction = Direction.getRandomDirection();
        speed = 2;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(randomGhostTexture.getSprite(direction, imageIndex % 2), x, y, 32, 32, null);
    }
}
