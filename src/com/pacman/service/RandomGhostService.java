package com.pacman.service;

public class RandomGhostService extends Enemy {

    public void tick() {
        randomMove();
    }

    @Override
    public void randomMove() {

        if (!moveToDirection(direction)) {
            direction = Direction.getRandomDirection();
        }
        imageTime++;

        if (imageTime == targetImageTime) {
            imageTime = 0;
            imageIndex++;
        }
    }
}