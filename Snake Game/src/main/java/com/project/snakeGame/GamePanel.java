package com.project.snakeGame;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FontMetrics;
import java.awt.event.ActionListener;

import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    public enum Mode { EASY, HARD }
    private static Mode gameMode = Mode.EASY;

    private static final int unitSize = 35;
    private static final int boardWidth = (SnakeGame.width / unitSize) * unitSize;
    private static final int boardHeight = (SnakeGame.height / unitSize) * unitSize;
    private static final int maxUnits = (boardHeight * boardWidth) / (unitSize * unitSize);

    private int bodyParts = 3;
    private int applesEaten = 0;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean isRunning = false;

    private final int[] x = new int[maxUnits];
    private final int[] y = new int[maxUnits];

    private Timer timer;
    private Random random;

    GamePanel() {
        random = new Random();
        setBackground(new Color(24,24,24));
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        setGameSpeed();
        startGame();
    }

    protected static void setMode(Mode mode) {
        gameMode = mode;
    }

    private void setGameSpeed() {
        int delay = (gameMode == Mode.EASY) ? 300 : 150;
        timer = new Timer(delay, this);
    }

    protected void startGame() {
        bodyParts = 3;
        applesEaten = 0;
        direction = 'R';
        isRunning = true;

        for (int i = 0; i < bodyParts; i++) {
            x[i] = (boardWidth / 2) - (i * unitSize);
            y[i] = (boardHeight / 2);
        }

        newApple();
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    protected void draw(Graphics g) {
        if (isRunning) {
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, unitSize, unitSize);

            for (int i = 0; i < bodyParts; i++) {
                g.setColor(i == 0 ? Color.GREEN : new Color(45, 180, 0));
                g.fillRect(x[i], y[i], unitSize, unitSize);
            }
        } else {
            gameOver(g);
        }
    }

    protected void newApple() {
        boolean valid;
        do {
            valid = true;
            appleX = random.nextInt(boardWidth / unitSize) * unitSize;
            appleY = random.nextInt(boardHeight / unitSize) * unitSize;

            for (int i = 0; i < bodyParts; i++) {
                if (appleX == x[i] && appleY == y[i]) {
                    valid = false;
                    break;
                }
            }
        } while (!valid);
    }

    protected void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U' -> y[0] -= unitSize;
            case 'D' -> y[0] += unitSize;
            case 'L' -> x[0] -= unitSize;
            case 'R' -> x[0] += unitSize;
        }
    }

    protected void checkApple() {
        Rectangle snakeHead = new Rectangle(x[0], y[0], unitSize, unitSize);
        Rectangle apple = new Rectangle(appleX, appleY, unitSize, unitSize);

        if (snakeHead.intersects(apple)) {
            bodyParts++;
            applesEaten++;
            SwingUtilities.invokeLater(() -> SnakeGame.scoreBoard.setText("Score : " + applesEaten));
            newApple();
        }
    }

    private void checkCollisions() {
        for (int i = 1; i < bodyParts; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {
                isRunning = false;
                break;
            }
        }

        if (x[0] < 0 || x[0] >= boardWidth || y[0] < 0 || y[0] >= boardHeight) {
            isRunning = false;
        }

        if (!isRunning) timer.stop();
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics = g.getFontMetrics();
        int x = (boardWidth - metrics.stringWidth("Game Over")) / 2;
        int y = boardHeight / 2;
        g.drawString("Game Over", x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            move();
            checkCollisions();
            checkApple();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!isRunning) return;

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> { if (direction != 'R') direction = 'L'; }
                case KeyEvent.VK_RIGHT -> { if (direction != 'L') direction = 'R'; }
                case KeyEvent.VK_UP -> { if (direction != 'D') direction = 'U'; }
                case KeyEvent.VK_DOWN -> { if (direction != 'U') direction = 'D'; }
            }
        }
    }
}
