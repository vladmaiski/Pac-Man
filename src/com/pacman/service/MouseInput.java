package com.pacman.service;

import com.pacman.model.Hero;
import com.pacman.model.Level;
import com.pacman.model.Menu;
import com.pacman.model.PlayBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private Audio a_click = new Audio("res/sound/menu/click.wav", 0.5);
    private String HELP_BTN = "Use the W, S, D, and A keys to navigate. Collect all the pills to go to the next level.\n Avoid ghosts. Good luck ...\nDeveloped by Vladislav Maiski";

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (Game.gameState == GameState.MENU) {
            Rectangle mouseRange = new Rectangle(e.getX(), e.getY(), 1, 1);
            if (mouseRange.intersects(Menu.playButton)) {
                a_click.sound();
                PlayBoard.currentLvl = 0;
                Game.playBoard.setLevel(PlayBoard.currentLvl);
                Game.gameState = GameState.GAME;
                Hero.score = 0;
            }
            if (mouseRange.intersects(Menu.quitButton)) {
                a_click.sound();
                System.exit(0);
            }
            if (mouseRange.intersects(Menu.helpButton)) {
                a_click.sound();
                JOptionPane.showMessageDialog(Game.gameFrame, HELP_BTN, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
