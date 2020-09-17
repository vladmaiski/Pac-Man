package com.pacman.model;

import com.pacman.service.Direction;
import com.pacman.service.GameObject;
import com.pacman.service.TargetGhostService;

import java.awt.*;

public class TargetGhost extends TargetGhostService {

    private Texture targetGhostTexture;

    public TargetGhost(int x, int y) {
        startX = x;
        startY = y;
        setBounds(x, y, Level.BLOCK_SIZE, Level.BLOCK_SIZE);
        direction = Direction.getRandomDirection();
        targetGhostTexture = new Texture(GameObject.TARGET_GHOST);
        speed = 1;
    }

    public void render(Graphics graphics) {
        if (state == State.SMART) {
            graphics.drawImage(targetGhostTexture.getSprite(lastDirection, imageIndex % 2), x, y, 32, 32, null);
        } else {
            graphics.drawImage(targetGhostTexture.getSprite(direction, imageIndex % 2), x, y, 32, 32, null);
        }

    }

}
