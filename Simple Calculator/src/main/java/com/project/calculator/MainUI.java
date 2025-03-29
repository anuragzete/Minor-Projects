package com.project.calculator;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.GridLayout;

/**
 * The {@code MainUI} class represents the panel containing all calculator buttons.
 * <p>
 * It is responsible for:
 * </p>
 * <ul>
 *     <li>Creating and styling the calculator buttons.</li>
 *     <li>Setting up the layout for the button panel.</li>
 *     <li>Attaching action listeners to each button, triggering operations.</li>
 * </ul>
 * <p>
 * This class integrates with {@code BaseUI} and uses {@code Logic} to perform
 * the necessary calculations.
 * </p>
 */
public class MainUI extends JPanel {

    /**
     * Array of buttons representing the calculator keys.
     */
    private final JButton[] buttons = new JButton[20];

    /**
     * Labels for the buttons, representing digits, operations, and functions.
     */
    private final String[] buttonLabel = {
            "CE", "C", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", "00", ".", "="
    };

    /**
     * The current input being displayed on the calculator.
     * <p>
     * It is updated as the user clicks buttons.
     * </p>
     */
    protected StringBuilder input = new StringBuilder();

    /**
     * The string representation of the current input.
     */
    protected String inputString;

    /**
     * The result of the last calculation performed.
     */
    protected double result;

    /**
     * Constructs the {@code MainUI} panel and initializes the calculator buttons.
     * <p>
     * It configures the panel layout, creates buttons with appropriate labels,
     * sets colors, fonts, and attaches action listeners for operations.
     * </p>
     *
     * @param baseUiInstance The {@code BaseUI} instance to access global properties like colors and theme.
     */
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
