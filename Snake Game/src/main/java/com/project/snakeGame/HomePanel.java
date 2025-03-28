package com.project.snakeGame;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel implements ActionListener {
    private JPanel mainMenu;
    private CardLayout cardLayout;
    private JButton easyMode, hardMode, exitGame;

    public HomePanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        mainMenu = new JPanel(new GridBagLayout());

        setupMainMenu();

        add(mainMenu, "MainMenu");

        cardLayout.show(this, "MainMenu");
    }

    private void setupMainMenu() {
        mainMenu.setBackground(new Color(24,24,24));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.gridx = 0;

        JLabel title = new JLabel("SNAKE GAME");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.CYAN);

        easyMode = createButton("Easy Mode");
        hardMode = createButton("Hard Mode");
        exitGame = createButton("Exit Game");

        easyMode.addActionListener(e -> {
            GamePanel.setMode(GamePanel.Mode.EASY);
            SnakeGame.startGame();
        });

        hardMode.addActionListener(e -> {
            GamePanel.setMode(GamePanel.Mode.HARD);
            SnakeGame.startGame();
        });

        exitGame.addActionListener(e -> SnakeGame.exitGame());

        gbc.gridy = 0; mainMenu.add(title, gbc);
        gbc.gridy = 1; mainMenu.add(easyMode, gbc);
        gbc.gridy = 2; mainMenu.add(hardMode, gbc);
        gbc.gridy = 3; mainMenu.add(exitGame, gbc);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(34, 177, 76)); // Green color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 160, 0)); // Darker Green on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(34, 177, 76));
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
