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

/**
 * The {@code HomePanel} class represents the main menu panel
 * for the Snake Game. It allows the user to select between:
 * <ul>
 *     <li>Easy Mode</li>
 *     <li>Hard Mode</li>
 *     <li>Exit the game</li>
 * </ul>
 * <p>
 * The panel uses a {@code CardLayout} for future extensibility,
 * making it easy to switch between different panels (e.g., game panel, settings panel).
 * </p>
 */
public class HomePanel extends JPanel implements ActionListener {

    /** The main menu panel that holds the buttons and title. */
    private JPanel mainMenu;

    /** The layout manager that handles panel switching. */
    private CardLayout cardLayout;

    /** Button for starting the game in Easy mode. */
    private JButton easyMode;

    /** Button for starting the game in Hard mode. */
    private JButton hardMode;

    /** Button for exiting the game. */
    private JButton exitGame;

    /**
     * Constructs the {@code HomePanel} and initializes the layout and components.
     * <p>
     * Uses {@code CardLayout} for future expansion, allowing the addition of more panels.
     * </p>
     */
    public HomePanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        mainMenu = new JPanel(new GridBagLayout());

        setupMainMenu();

        add(mainMenu, "MainMenu");

        cardLayout.show(this, "MainMenu");
    }

    /**
     * Sets up the main menu with buttons and a title.
     * <p>
     * It uses {@code GridBagLayout} to arrange the components vertically.
     * </p>
     */
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

    /**
     * Creates a styled button with hover effects.
     * <p>
     * The button has the following properties:
     * </p>
     * <ul>
     *     <li>Font: {@code Arial, BOLD, 20}</li>
     *     <li>Foreground color: {@code WHITE}</li>
     *     <li>Background color: {@code Green} (darkens on hover)</li>
     *     <li>Padding and border adjustments</li>
     * </ul>
     *
     * @param text The label text for the button.
     * @return The styled {@code JButton} with hover effects.
     */
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

    /**
     * Handles button click actions.
     * <p>
     * Currently not used, but required for {@code ActionListener} implementation.
     * </p>
     *
     * @param e The action event triggered by button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {}
}
