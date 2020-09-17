package com.pacman.service;

import com.pacman.model.Level;
import com.pacman.model.Movable;
import com.pacman.model.PlayBoard;

import static com.pacman.service.PlayBoardService.hero;

public class HeroService extends Movable {

    public Direction currentDirection = Direction.LEFT;
    public Direction nextDirection = null;

    private int time = 0;
    private final int targetTime = 15;
    public int imageIndex = 0;
    public static int score = 0;
    public static int health = 3;

    public void tick() {
        moveToDirection(currentDirection);
        if (nextDirection != null && nextDirection != currentDirection) {
            if (isPossibleMoveToDirection(nextDirection)) {
                currentDirection = nextDirection;
                nextDirection = null;
            }
        }

        Level level = PlayBoard.level;

        for (int i = 0; i < level.tablets.size(); i++) {
            if (this.intersects(level.tablets.get(i))) {
                score += 10;
                PlayBoard.A_COIN.sound();
                level.tablets.remove(i);
                break;
            }
        }

        for (int i = 0; i < level.boosters.size(); i++) {
            if (this.intersects(level.boosters.get(i))) {
                level.boosters.remove(i);
                score += 50;
                break;
            }
        }

        for (int i = 0; i < level.targetGhosts.size(); i++) {
            if (this.intersects(level.targetGhosts.get(i))) {
                die();
                break;
            }
        }

        for (int i = 0; i < level.randomGhosts.size(); i++) {
            if (this.intersects(level.randomGhosts.get(i))) {
                die();
                break;
            }
        }

        time++;

        if (time == targetTime) {
            time = 0;
            imageIndex++;
        }
    }

    public void die() {
        health--;
        PlayBoard.level.respawnActors();
        if (health == 0) {
            PlayBoard.A_PAC_MAN_DEATH.sound();
            Game.gameState = GameState.MENU;
            health = 3;
        }
    }

    private boolean isPossibleMoveToDirection(Direction direction) {
        int x = hero.x;
        int y = hero.y;
        switch (direction) {
            case RIGHT:
                x += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;

        }
        return isPossibleToMove(x, y);
    }
}
