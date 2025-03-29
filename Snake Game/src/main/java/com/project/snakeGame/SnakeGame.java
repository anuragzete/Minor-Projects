package com.project.snakeGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

/**
 * The {@code SnakeGame} class is the main entry point for the Snake game.
 * It creates the game window, initializes the UI components, and handles
 * game transitions between the home screen and the game panel.
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class SnakeGame extends JFrame {

    /** The width of the game window. */
    public static final int width = 800;

    /** The height of the game window. */
    public static final int height = 650;

    /** The home panel displaying the main menu. */
    public static HomePanel homePanel;

    /** The game panel displaying the snake game. */
    public static GamePanel gamePanel;

    /** The scoreboard label showing the player's score. */
    public static JLabel scoreBoard;

    /**
     * Constructs the Snake game window, setting its properties and
     * initializing the GUI components.
     */
    public SnakeGame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);

        // Initialize UI Components
        createGUI();

        setVisible(true);
    }

    /**
     * Creates and initializes the GUI components, including the
     * scoreboard, home panel, and game panel.
     */
    private void createGUI() {
        scoreBoard = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreBoard.setFont(new Font("Arial", Font.BOLD, 50));
        scoreBoard.setForeground(Color.WHITE);
        scoreBoard.setBackground(new Color(32,32,32));
        scoreBoard.setOpaque(true);

        homePanel = new HomePanel();
        gamePanel = new GamePanel();

        setLayout(new BorderLayout());
        add(scoreBoard, BorderLayout.NORTH);
        add(homePanel, BorderLayout.CENTER); // Show home screen first
    }

    /**
     * The main method that launches the Snake game using Swing's event-dispatch thread.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SnakeGame::new);
    }

    /**
     * Starts the game by switching from the home panel to the game panel.
     * It removes the home screen, adds the game screen, and starts the game loop.
     */
    public static void startGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(homePanel);
        if (frame != null) {
            frame.remove(homePanel);
            gamePanel = new GamePanel();
            frame.add(gamePanel);
            frame.revalidate();
            frame.repaint();
            gamePanel.requestFocus();
            gamePanel.startGame();
        }
    }

    /**
     * Exits the game by terminating the application.
     */
    public static void exitGame() {
        System.exit(0);
    }
}
