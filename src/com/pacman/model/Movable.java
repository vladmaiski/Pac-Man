package com.pacman.model;

import com.pacman.service.Direction;

import java.awt.*;

public abstract class Movable extends Rectangle {

    public int speed;
    public int startX;
    public int startY;

    public boolean isPossibleToMove(int xNext, int yNext) {
        Rectangle bounds = new Rectangle(xNext, yNext, width, height);
        Level level = PlayBoard.level;
        boolean isPossible = true;
        int i = 0;
        while (isPossible && i < level.walls.size()) {
            if (bounds.intersects(level.walls.get(i)))
                isPossible = false;
            i++;
        }
        return isPossible;
    }

    public boolean moveToDirection(Direction direction) {
        boolean move = false;
        switch (direction) {
            case RIGHT:
                if (isPossibleToMove(x + speed, y)) {
                    x += speed;
                    move = true;
                }
                break;
            case LEFT:
                if (isPossibleToMove(x - speed, y)) {
                    x -= speed;
                    move = true;
                }
                break;
            case UP:
                if (isPossibleToMove(x, y - speed)) {
                    y -= speed;
                    move = true;
                }
                break;
            case DOWN:
                if (isPossibleToMove(x, y + speed)) {
                    y += speed;
                    move = true;
                }
                break;
        }
        return move;
    }

    public void respawn() {
        x = startX;
        y = startY;
    }

}