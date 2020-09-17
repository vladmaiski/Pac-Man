package com.pacman.model;

import java.awt.*;

public class Tablet extends Rectangle {

    public Tablet(int x, int y) {
        setBounds(x + 12, y + 12, 8, 8);
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(x, y, 8, 8);
    }
}
