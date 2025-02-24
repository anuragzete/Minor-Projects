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

public class BaseUI extends JFrame {
    protected final int HEIGHT = 600;
    protected final int WIDTH = 500;
    protected final Color PRIMARY_DARK_COLOR = new Color(38, 38, 38);
    protected final Color SECONDARY_DARK_COLOR = new Color(30, 30, 30);
    protected final Color PRIMARY_LIGHT_COLOR = new Color(222, 222, 222);
    protected final Color SECONDARY_LIGHT_COLOR = new Color(225, 225, 225);

    protected static final boolean isDarkMode = true;

    protected JPanel displayHolder = new JPanel();
    protected JLabel history = new JLabel();
    protected JLabel display = new JLabel();

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
