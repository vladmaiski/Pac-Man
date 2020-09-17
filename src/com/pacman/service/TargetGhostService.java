package com.pacman.service;

public class TargetGhostService extends Enemy {

    public void tick() {
        if (state == State.RANDOM)
            randomMove();

        if (state == State.SMART)
            smartMove();

        if (state == State.FIND_PATH)
            findPathMove();

        imageTime++;

        if (imageTime == targetImageTime) {
            imageTime = 0;
            imageIndex++;
        }
    }
}
