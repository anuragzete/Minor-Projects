package com.project.calculator;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.UIManager;

/**
 * The {@code BaseUI} class represents the main graphical user interface for a simple calculator application.
 * <p>
 * It sets up the main frame, display panels, and handles theme selection (dark/light mode).
 * It uses the {@code FlatLaf} library for a modern UI look and feel.
 * </p>
 *
 * <p>
 * The class creates and initializes the base layout, display area, and integrates the {@code MainUI}
 * component, which contains the calculator buttons and functionalities.
 * </p>
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class BaseUI extends JFrame {

    /**
     * The height of the application window.
     */
    protected final int HEIGHT = 600;

    /**
     * The width of the application window.
     */
    protected final int WIDTH = 500;

    /**
     * The primary color used for the dark mode background.
     */
    protected final Color PRIMARY_DARK_COLOR = new Color(38, 38, 38);

    /**
     * The secondary color used for the dark mode panel background.
     */
    protected final Color SECONDARY_DARK_COLOR = new Color(30, 30, 30);

    /**
     * The primary color used for the light mode background.
     */
    protected final Color PRIMARY_LIGHT_COLOR = new Color(222, 222, 222);

    /**
     * The secondary color used for the light mode panel background.
     */
    protected final Color SECONDARY_LIGHT_COLOR = new Color(225, 225, 225);

    /**
     * Flag to determine whether the application should use dark mode or light mode.
     * <p>
     * If {@code true}, the dark mode theme is applied. Otherwise, light mode is used.
     * </p>
     */
    protected static final boolean isDarkMode = true;

    /**
     * The main panel that holds the display and history labels.
     */
    protected JPanel displayHolder = new JPanel();

    /**
     * Label to display the calculation history.
     */
    protected JLabel history = new JLabel();

    /**
     * Label to display the current input or result of the calculator.
     */
    protected JLabel display = new JLabel();

    /**
     * Initializes the display panel and sets up colors, fonts, and layout based on the selected theme.
     * <p>
     * The initialization block configures:
     * <ul>
     *     <li>Panel dimensions and layout.</li>
     *     <li>Label fonts, colors, and alignments.</li>
     *     <li>Applies dark or light mode color scheme.</li>
     * </ul>
     */
    {
        displayHolder.setPreferredSize(new Dimension(WIDTH, 100));
        displayHolder.setLayout(new BorderLayout());

        history.setPreferredSize(new Dimension(WIDTH, 50));
        history.setFont(new Font("Arial", Font.PLAIN, 25));
        history.setOpaque(true);
        history.setHorizontalAlignment(SwingConstants.LEFT);

        display.setPreferredSize(new Dimension(WIDTH, 50));
        display.setFont(new Font("Arial", Font.BOLD, 40));
        display.setText("0");
        display.setOpaque(true);
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        if (isDarkMode) {
            this.setBackground(PRIMARY_DARK_COLOR);
            displayHolder.setBorder(BorderFactory.createLineBorder(PRIMARY_DARK_COLOR, 3));
            history.setBackground(PRIMARY_DARK_COLOR);
            history.setForeground(Color.WHITE);
            display.setBackground(PRIMARY_DARK_COLOR);
            display.setForeground(Color.WHITE);
        } else {
            this.setBackground(PRIMARY_LIGHT_COLOR);
            displayHolder.setBorder(BorderFactory.createLineBorder(PRIMARY_LIGHT_COLOR, 3));
            history.setBackground(PRIMARY_LIGHT_COLOR);
            history.setForeground(Color.BLACK);
            display.setBackground(PRIMARY_LIGHT_COLOR);
            display.setForeground(Color.BLACK);
        }
    }

    /**
     * Constructor for the {@code BaseUI} class.
     * <p>
     * Initializes the main frame, sets the title, icon, and configures the layout.
     * <p>
     * It:
     * <ul>
     *     <li>Loads the main UI components by creating an instance of {@code MainUI}.</li>
     *     <li>Registers key listeners for keyboard events.</li>
     *     <li>Sets the frame properties such as size, location, and visibility.</li>
     * </ul>
     */
    BaseUI() {
        MainUI mainUiInstance = new MainUI(this);

        this.setTitle("Simple Calculator");
        this.setIconImage(new ImageIcon(getClass().getResource("/icons8-calculator-48.png")).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setFocusable(true);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        displayHolder.add(history, BorderLayout.NORTH);
        displayHolder.add(display, BorderLayout.CENTER);

        this.addKeyListener(new KeyboardEvents(this, mainUiInstance));
        this.add(displayHolder, BorderLayout.NORTH);
        this.add(mainUiInstance, BorderLayout.CENTER);

        this.requestFocusInWindow();
        displayHolder.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                displayHolder.requestFocusInWindow();
            }
        });

        this.setVisible(true);

    }

    /**
     * The main entry point of the application.
     * <p>
     * It applies the selected theme (dark or light mode) using {@code FlatLaf} for UI consistency.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        if (isDarkMode) {
            try {
                new UIManager().setLookAndFeel(new FlatDarkLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                new UIManager().setLookAndFeel(new FlatLightLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        new BaseUI();
    }
}
