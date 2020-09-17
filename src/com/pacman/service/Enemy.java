package com.pacman.service;

import com.pacman.model.Movable;

import static com.pacman.service.PlayBoardService.hero;

public abstract class Enemy extends Movable {

    public enum State {
        RANDOM,
        SMART,
        FIND_PATH
    }

    public int time = 0;
    public final int targetTime = 60 * 4;
    public final int targetImageTime = 15;
    public int imageTime = 0;
    public int imageIndex = 0;

    public Direction lastDirection;
    public TargetGhostService.State state = TargetGhostService.State.RANDOM;
    public Direction direction;


    public void randomMove() {
        switch (direction) {
            case RIGHT:
                if (isPossibleToMove(x + speed, y)) {
                    x += speed;
                } else {
                    direction = Direction.getRandomDirection();
                }
                break;
            case LEFT:
                if (isPossibleToMove(x - speed, y)) {
                    x -= speed;
                } else {
                    direction = Direction.getRandomDirection();
                }
                break;
            case UP:
                if (isPossibleToMove(x, y - speed)) {
                    y -= speed;
                } else {
                    direction = Direction.getRandomDirection();
                }
                break;
            case DOWN:
                if (isPossibleToMove(x, y + speed)) {
                    y += speed;
                } else {
                    direction = Direction.getRandomDirection();
                }
                break;
        }
        time++;

        if (time == targetTime) {
            state = State.SMART;
            time = 0;
        }
    }

    public void smartMove() {
        boolean inMove = false;

        if (x < hero.x && isPossibleToMove(x + speed, y)) {
            x += speed;
            lastDirection = Direction.RIGHT;
            inMove = true;
        }

        if (x > hero.x && isPossibleToMove(x - speed, y)) {
            x -= speed;
            lastDirection = Direction.LEFT;
            inMove = true;
        }

        if (y > hero.y && isPossibleToMove(x, y - speed)) {
            y -= speed;
            lastDirection = Direction.UP;
            inMove = true;
        }

        if (y < hero.y && isPossibleToMove(x, y + speed)) {
            y += speed;
            lastDirection = Direction.DOWN;
            inMove = true;
        }

        if (x == hero.x) {
            inMove = true;
        }
        if (!inMove) {
            state = State.FIND_PATH;
        }

        time++;

        if (time == targetTime) {
            state = State.RANDOM;
            time = 0;
        }
    }

    public void findPathMove() {
        if (lastDirection != null)
            switch (lastDirection) {
                case RIGHT:
                    if (y < hero.y && isPossibleToMove(x, y + speed)) {
                        y += speed;
                        state = State.SMART;
                    } else if (isPossibleToMove(x, y - speed)) {
                        y -= speed;
                        state = State.SMART;
                    }
                    if (isPossibleToMove(x + speed, y))
                        x += speed;
                    break;
                case LEFT:
                    if (y < hero.y && isPossibleToMove(x, y + speed)) {
                        y += speed;
                        state = State.SMART;
                    } else if (isPossibleToMove(x, y - speed)) {
                        y -= speed;
                        state = State.SMART;
                    }

                    if (isPossibleToMove(x - speed, y))
                        x -= speed;
                    break;
                case UP:
                    if (x < hero.x && isPossibleToMove(x + speed, y)) {
                        x += speed;
                        state = State.SMART;
                    } else if (isPossibleToMove(x - speed, y)) {
                        x -= speed;
                        state = State.SMART;
                    }

                    if (isPossibleToMove(x, y - speed))
                        y -= speed;
                    break;
                case DOWN:
                    if (x < hero.x && isPossibleToMove(x + speed, y)) {
                        x += speed;
                        state = State.SMART;
                    } else if (isPossibleToMove(x - speed, y)) {
                        x -= speed;
                        state = State.SMART;
                    }

                    if (isPossibleToMove(x, y + speed))
                        y += speed;
                    break;
            }

        time++;

        if (time == targetTime) {
            state = State.RANDOM;
            time = 0;
        }
    }

}
