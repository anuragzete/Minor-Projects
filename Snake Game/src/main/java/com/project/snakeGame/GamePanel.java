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

/**
 * The {@code GamePanel} class handles the gameplay logic and rendering
 * for the Snake Game. It manages:
 * <ul>
 *     <li>Snake movement and collision detection</li>
 *     <li>Apple generation and score tracking</li>
 *     <li>Game rendering and updating</li>
 * </ul>
 * <p>
 * The game can run in two modes:
 * </p>
 * <ul>
 *     <li>{@code EASY} mode: Slower snake speed</li>
 *     <li>{@code HARD} mode: Faster snake speed</li>
 * </ul>
 */
public class GamePanel extends JPanel implements ActionListener {

    /** Enum for game difficulty modes. */
    public enum Mode { EASY, HARD }

    /** Current game mode (default: EASY). */
    private static Mode gameMode = Mode.EASY;

    /** Size of each unit (square) on the game board. */
    private static final int unitSize = 35;

    /** Width of the game board (rounded to fit units). */
    private static final int boardWidth = (SnakeGame.width / unitSize) * unitSize;

    /** Height of the game board (rounded to fit units). */
    private static final int boardHeight = (SnakeGame.height / unitSize) * unitSize;

    /** Maximum number of units (board area divided by unit size). */
    private static final int maxUnits = (boardHeight * boardWidth) / (unitSize * unitSize);

    /** Initial length of the snake. */
    private int bodyParts = 3;

    /** Score - number of apples eaten. */
    private int applesEaten = 0;

    /** X and Y positions of the apple. */
    private int appleX, appleY;

    /** Current direction of the snake's movement. */
    private char direction = 'R';

    /** Game running state. */
    private boolean isRunning = false;

    /** Arrays storing the snake's X and Y coordinates. */
    private final int[] x = new int[maxUnits];
    private final int[] y = new int[maxUnits];

    /** Timer to control the game speed and refresh rate. */
    private Timer timer;

    /** Random number generator for apple positioning. */
    private Random random;

    /**
     * Constructs the {@code GamePanel}, initializes the game settings,
     * and starts the game.
     */
    GamePanel() {
        random = new Random();
        setBackground(new Color(24,24,24));
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        setGameSpeed();
        startGame();
    }

    /**
     * Sets the game mode (difficulty).
     *
     * @param mode The selected game mode (EASY or HARD).
     */
    protected static void setMode(Mode mode) {
        gameMode = mode;
    }

    /**
     * Configures the game speed based on the selected mode.
     * <ul>
     *     <li>{@code EASY} mode: 300ms delay</li>
     *     <li>{@code HARD} mode: 150ms delay</li>
     * </ul>
     */
    private void setGameSpeed() {
        int delay = (gameMode == Mode.EASY) ? 300 : 150;
        timer = new Timer(delay, this);
    }

    /**
     * Starts the game by:
     * <ul>
     *     <li>Resetting snake length and score</li>
     *     <li>Setting the initial direction to 'R'</li>
     *     <li>Spawning the first apple</li>
     *     <li>Starting the game timer</li>
     * </ul>
     */
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

    /**
     * Handles the painting of the game board.
     *
     * @param g The {@code Graphics} object used for rendering.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Draws the game elements:
     * <ul>
     *     <li>Apple</li>
     *     <li>Snake body</li>
     *     <li>Game over message (if the game ends)</li>
     * </ul>
     *
     * @param g The {@code Graphics} object used for rendering.
     */
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

    /**
     * Spawns a new apple at a random location,
     * ensuring it doesn't overlap with the snake's body.
     */
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

    /**
     * Moves the snake in the current direction.
     */
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

    /**
     * Checks if the snake has eaten an apple.
     * Increases the snake's length and score if true.
     */
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

    /**
     * Checks for collisions with the snake itself or the game boundaries.
     * Stops the game if a collision occurs.
     */
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

    /**
     * Displays the "Game Over" message.
     *
     * @param g The {@code Graphics} object used for rendering.
     */
    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics = g.getFontMetrics();
        int x = (boardWidth - metrics.stringWidth("Game Over")) / 2;
        int y = boardHeight / 2;
        g.drawString("Game Over", x, y);
    }

    /**
     * Handles the game loop by updating the snake's position,
     * checking for collisions, and determining if the snake
     * has eaten an apple. The game is repainted after each tick.
     *
     * @param e The {@code ActionEvent} triggered by the timer.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            move();
            checkCollisions();
            checkApple();
        }
        repaint();
    }

    /**
     * The {@code MyKeyAdapter} class handles keyboard inputs
     * to control the snake's direction. It listens for arrow key
     * presses and updates the snake's direction accordingly.
     */
    public class MyKeyAdapter extends KeyAdapter {

        /**
         * Listens for key presses and changes the snake's
         * direction based on the arrow keys. Prevents the
         * snake from moving in the opposite direction.
         *
         * @param e The {@code KeyEvent} triggered by the key press.
         */
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
