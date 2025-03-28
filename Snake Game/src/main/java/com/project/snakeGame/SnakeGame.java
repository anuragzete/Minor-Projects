package com.project.snakeGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

public class SnakeGame extends JFrame {
    public static final int width = 800;
    public static final int height = 650;
    public static HomePanel homePanel;
    public static GamePanel gamePanel;
    public static JLabel scoreBoard;

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SnakeGame::new);
    }

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


    public static void exitGame() {
        System.exit(0);
    }
}
