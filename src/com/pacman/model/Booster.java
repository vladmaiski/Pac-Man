package com.pacman.model;

import java.awt.*;

public class Booster extends Rectangle {

    public Booster(int x, int y) {
        setBounds(x + 8, y + 8, 8, 8);
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(x, y, 16, 16);
    }
}
