package com.pacman.model;

import com.pacman.service.LevelService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Level extends LevelService {

    public static final int BLOCK_SIZE = 32;

    public int height;
    public int width;
    private Bar bar;

    public Level(String path) {
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            this.height = map.getHeight();
            this.width = map.getWidth();
            int[] pixels = new int[width * height];
            map.getRGB(0, 0, width, height, pixels, 0, width);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int colorValue = pixels[x + (y * width)];
                    addObject(colorValue, x * Level.BLOCK_SIZE, y * Level.BLOCK_SIZE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bar = new Bar();
    }

    public void render(Graphics graphics) {
        for (Wall b : walls) {
            b.render(graphics);
        }
        for (Booster b : boosters) {
            b.render(graphics);
        }
        for (Tablet t : tablets) {
            t.render(graphics);
        }
        for (TargetGhost targetGhost : targetGhosts) {
            targetGhost.render(graphics);
        }
        for (RandomGhost randomGhost: randomGhosts) {
            randomGhost.render(graphics);
        }
        bar.render(graphics);
    }
}
