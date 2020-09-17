package com.pacman.model;

import com.pacman.service.Game;
import com.pacman.service.GameObject;

import java.awt.*;


public class Bar {

    private final Image healthImage;

    public final int offset = Level.BLOCK_SIZE + 4;

    public Bar() {
        Texture healthTexture = new Texture(GameObject.HEALTH);
        healthImage = healthTexture.getSprite(null, 0);
    }

    public void render(Graphics graphics) {
        graphics.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 24));
        graphics.setColor(Color.WHITE);
        graphics.drawString("Score: " + PlayBoard.hero.score, 0, Game.HEIGHT - 10);
        graphics.drawString("Level: " + (PlayBoard.currentLvl + 1), Game.WIDTH - 150, Game.HEIGHT - 10);

        for (int i = 0; i < PlayBoard.hero.health; i++) {
            graphics.drawImage(healthImage, ((Game.WIDTH / 2 - 100) + i * offset), Game.HEIGHT - Level.BLOCK_SIZE, 32,32, null);
        }
    }

}
