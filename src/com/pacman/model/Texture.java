package com.pacman.model;

import com.pacman.service.Direction;
import com.pacman.service.GameObject;

import java.awt.image.BufferedImage;

public class Texture {

    private SpriteSheet spriteSheet = new SpriteSheet("/res/spritesheet/spritesheet.png");

    private BufferedImage[] right = new BufferedImage[2];
    private BufferedImage[] left = new BufferedImage[2];
    private BufferedImage[] up = new BufferedImage[2];
    private BufferedImage[] down = new BufferedImage[2];

    public Texture(GameObject actor) {
        loadSprites(actor);
    }

    private void loadMovable(int line) {
        right[0] = spriteSheet.getSprite(0, line);
        right[1] = spriteSheet.getSprite(1, line);
        left[0] = spriteSheet.getSprite(2, line);
        left[1] = spriteSheet.getSprite(3, line);
        up[0] = spriteSheet.getSprite(4, line);
        up[1] = spriteSheet.getSprite(5, line);
        down[0] = spriteSheet.getSprite(6, line);
        down[1] = spriteSheet.getSprite(7, line);
    }

    private void loadSprites(GameObject actor) {
        switch (actor) {
            case TARGET_GHOST:
                loadMovable(3);
                break;
            case RANDOM_GHOST:
                loadMovable(1);
                break;
            case PACMAN:
                loadMovable(0);
                break;
            case WALL:
                right[0] = spriteSheet.getSprite(0,2);
                break;
            case HEALTH:
                right[0] = spriteSheet.getSprite(1,2);
                break;
        }
    }

    public BufferedImage getSprite(Direction direction, int index) {
        BufferedImage resImage = null;

        if (direction == null) {
            return right[0];
        }

        switch (direction) {
            case UP:
                if (index == 0) {
                    resImage = up[0];
                } else {
                    resImage = up[1];
                }
                break;
            case LEFT:
                if (index == 0) {
                    resImage = left[0];
                } else {
                    resImage = left[1];
                }
                break;
            case DOWN:
                if (index == 0) {
                    resImage = down[0];
                } else {
                    resImage = down[1];
                }
                break;
            case RIGHT:
                if (index == 0) {
                    resImage = right[0];
                } else {
                    resImage = right[1];
                }
                break;
        }
        return resImage;
    }
}
