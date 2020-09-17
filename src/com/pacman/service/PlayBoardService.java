package com.pacman.service;

import com.pacman.model.Hero;
import com.pacman.model.Level;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayBoardService {

    public static int currentLvl = 0;
    private final int MAX_LVL = 1;
    public static String levelPath = "/res/map/map" + currentLvl + ".png";
    public static final Audio A_COIN = new Audio("res/sound/game/coin.wav", 0.5);;
    public static Audio A_PAC_MAN_DEATH = new Audio("res/sound/game/pacman_death.wav", 0.5);

    public static Level level;
    public static Hero hero;

    public void tick() {
        hero.tick();
        level.tick();
        if (level.tablets.size() == 0 && level.boosters.size() == 0) {
            nextLvl();
        }
    }

    public void setLevel(int number) {
        level = new Level("/res/map/map" + number + ".png");
    }

    public void nextLvl() {
        if (currentLvl + 1 <= MAX_LVL) {
            currentLvl++;
        }
        level = new Level("/res/map/map" + currentLvl + ".png");
    }
}
