package com.pacman.service;

import java.util.Random;

public enum Direction {
    RIGHT,
    LEFT,
    UP,
    DOWN;

    public static Direction getRandomDirection() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
