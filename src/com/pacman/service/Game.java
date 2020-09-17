package com.pacman.service;



import com.pacman.model.Menu;
import com.pacman.model.PlayBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener {

    private boolean isRunning = false;

    public static final int WIDTH = 640;
    public static final int HEIGHT = 512;
    private static final double TICK_RATE = 60.0;
    private static final String TITLE = "PAC-MAN 2";
    public static GameState gameState = GameState.MENU;
    public static JFrame gameFrame = new JFrame();


    public static Game game;
    public static Menu menu;
    public static PlayBoard playBoard;


    public static Thread thread;

    public Game() {
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        addKeyListener(this);
        addMouseListener(new MouseInput());
        menu = new Menu();
        playBoard = new PlayBoard();
    }

    private synchronized void start() {
        if (isRunning)
            return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }


    private synchronized void stop() {
        if (!isRunning)
            return;
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        if (gameState == GameState.GAME) {
            playBoard.render(graphics);
        } else if (gameState == GameState.MENU) {
            menu.render(graphics);
        }
        graphics.dispose();
        bufferStrategy.show();
    }

    public void tick() {
        if (gameState == GameState.GAME) {
            playBoard.tick();
        }
    }

    @Override
    public void run() {
        requestFocus();
        double timer = System.currentTimeMillis();
        long prefTime = System.nanoTime();
        int fps = 0;
        double delta = 0;
        double nanoSecs = 1e9 / TICK_RATE;

        while (isRunning) {
            long curTime = System.nanoTime();
            delta += (curTime - prefTime) / nanoSecs;
            prefTime = curTime;

            while (delta >= 1) {
                render();
                tick();
                delta--;
                fps++;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println(fps);
                timer += 1000;
                fps = 0;
            }
        }
        start();
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (Game.gameState == GameState.GAME) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_W || keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                PlayBoard.hero.nextDirection = Direction.UP;
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_S || keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                PlayBoard.hero.nextDirection = Direction.DOWN;
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_D || keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                PlayBoard.hero.nextDirection = Direction.RIGHT;
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_A || keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
               PlayBoard.hero.nextDirection = Direction.LEFT;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }


    public static void main(String[] args) {
        game = new Game();
        gameFrame.add(game);
        gameFrame.setTitle(TITLE);
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        game.start();
    }
}
