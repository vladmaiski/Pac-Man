package com.pacman.service;

import com.pacman.model.*;

import java.awt.*;
import java.util.ArrayList;

import static com.pacman.service.PlayBoardService.hero;

public class LevelService extends Canvas {

    public ArrayList<Wall> walls = new ArrayList<>();
    public ArrayList<Booster> boosters = new ArrayList<>();
    public ArrayList<Tablet> tablets = new ArrayList<>();
    public ArrayList<TargetGhost> targetGhosts = new ArrayList<>();
    public ArrayList<RandomGhost> randomGhosts = new ArrayList<>();


    public void respawnActors() {
        for(TargetGhost targetGhost: targetGhosts)
            targetGhost.respawn();
        for(RandomGhost randomGhost: randomGhosts)
            randomGhost.respawn();
        hero.respawn();
    }

    public void tick() {
        for (TargetGhost targetGhost : targetGhosts) {
            targetGhost.tick();
        }
        for (RandomGhost randomGhost: randomGhosts) {
            randomGhost.tick();
        }
    }

    public void addObject(int colorValue, int x, int y) {
        switch (colorValue) {
            case 0xFF000000:
                walls.add(new Wall(x, y));
                break;
            case 0xFFFFFF00:
                hero = new Hero(x, y);
                hero.startY = y;
                hero.startX = x;
                break;
            case 0xFFFFFFFF:
                boosters.add(new Booster(x, y));
                break;
            case 0xFFD50000:
                targetGhosts.add(new TargetGhost(x, y));
                tablets.add(new Tablet(x, y));
                break;
            case 0xFFAAAAAA:
                randomGhosts.add(new RandomGhost(x, y));
                tablets.add(new Tablet(x, y));
            default:
                tablets.add(new Tablet(x, y));
                break;
        }
    }
}
