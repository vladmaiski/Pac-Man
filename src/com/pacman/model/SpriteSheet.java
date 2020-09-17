package com.pacman.model;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

        public BufferedImage sheet;

        public SpriteSheet(String path) {
            try {
                sheet = ImageIO.read(getClass().getResource(path));
            } catch (IOException e) {
                System.out.println("IMAGE EXCEPTION");
            }
        }

        public BufferedImage getSprite(int x, int y) {
            return sheet.getSubimage(x * 16, y * 16,16, 16);
        }
}
