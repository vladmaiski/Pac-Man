package com.pacman.model;

import com.pacman.service.Game;
import com.pacman.service.*;
import java.awt.*;

public class PlayBoard extends PlayBoardService {

    public PlayBoard() {
        hero = new Hero(Game.WIDTH / 2, Game.HEIGHT / 2);
        level = new Level("/res/map/map" + currentLvl + ".png");
    }

    public void render(Graphics graphics) {
        hero.render(graphics);
        level.render(graphics);
    }

}
