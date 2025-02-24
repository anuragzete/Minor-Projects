package com.project.calculator;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.GridLayout;

public class MainUI extends JPanel {
    private final JButton[] buttons = new JButton[20];
    private final String[] buttonLabel = {
            "CE", "C", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", "00", ".", "="
    };

    protected StringBuilder input = new StringBuilder();
    protected String inputString;
    protected double result;

    MainUI(BaseUI baseUiInstance) {
        Logic logicInstance = new Logic(baseUiInstance , this);

        this.setPreferredSize(new Dimension(WIDTH, (HEIGHT - 150)));

        if (baseUiInstance.isDarkMode) {
            this.setBackground(baseUiInstance.PRIMARY_DARK_COLOR);
        } else {
            this.setBackground(baseUiInstance.PRIMARY_LIGHT_COLOR);
        }

        this.setOpaque(true);
        this.setLayout(new GridLayout(5, 4));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonLabel[i]);

            if (baseUiInstance.isDarkMode) {
                buttons[i].setBackground(baseUiInstance.SECONDARY_DARK_COLOR);
                buttons[i].setForeground(Color.WHITE);
                buttons[i].setBorder(BorderFactory.createLineBorder(baseUiInstance.PRIMARY_DARK_COLOR, 5, true));
            } else {
                this.setBackground(baseUiInstance.SECONDARY_LIGHT_COLOR);
                buttons[i].setForeground(Color.BLACK);
                buttons[i].setBorder(BorderFactory.createLineBorder(baseUiInstance.PRIMARY_LIGHT_COLOR, 5, true));
            }

            buttons[i].setFont(new Font("Arial", Font.PLAIN, 30));
            buttons[i].setOpaque(true);
            buttons[i].addActionListener(e -> {
                logicInstance.performAction(e);
            });

            this.add(buttons[i]);
        }
    }
}
