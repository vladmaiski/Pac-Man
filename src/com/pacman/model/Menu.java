package com.pacman.model;

import com.pacman.service.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.pacman.service.PlayBoardService.hero;

public class Menu {


    public static Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 50, 200, 100, 50);
    public static Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 50, 300, 100, 50);
    public static Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 50, 400, 100, 50);
    public Image menuImage;

    public Menu() {
        try {
            menuImage = ImageIO.read(getClass().getResource("/res/menu/picture_pacman.jpg"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Some files not found. Try to reinstall your application.","Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            e.printStackTrace();
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(menuImage, 0,0, Game.WIDTH + 400, Game.HEIGHT, null);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics.setFont(new Font("arial", Font.BOLD, 50));
        graphics.setColor(Color.YELLOW);
        graphics.drawString("PAC-MAN GAME", Game.WIDTH / 5, 100);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("arial", Font.BOLD, 20));
        graphics.drawString("Score: " + Integer.toString(hero.score), Game.WIDTH / 2 - 50, 150);

        graphics.setFont(new Font("arial", Font.ITALIC + Font.BOLD, 30));

        graphics.drawString("Play", playButton.x + 19, playButton.y + 35);
        graphics2D.draw(playButton);

        graphics.drawString("Help", helpButton.x + 19, helpButton.y + 35);
        graphics2D.draw(helpButton);

        graphics.drawString("Exit", quitButton.x + 19, quitButton.y + 35);
        graphics2D.draw(quitButton);

        graphics.setFont(new Font("arial", Font.ITALIC, 14));
        graphics.drawString("Developed by Vladislav Maiski, BSUIR ", 0, Game.HEIGHT - graphics.getFont().getSize());
    }

}