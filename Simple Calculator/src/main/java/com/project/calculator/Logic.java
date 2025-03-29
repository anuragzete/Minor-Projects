package com.project.calculator;

import java.awt.event.ActionEvent;

/**
 * The {@code Logic} class handles the core operations and event handling for the calculator.
 * <p>
 * It performs the following tasks:
 * </p>
 * <ul>
 *     <li>Processes button click events.</li>
 *     <li>Handles mathematical operations and displays the result.</li>
 *     <li>Manages clearing the display and maintaining history.</li>
 * </ul>
 * <p>
 * This class interacts with both the {@code BaseUI} and {@code MainUI} to
 * display the results and maintain the input history.
 * </p>
 */
public class Logic {

    /**
     * Reference to the {@code BaseUI} instance for accessing display and history labels.
     */
    private final BaseUI baseUiInstance;

    /**
     * Reference to the {@code MainUI} instance for handling input and result operations.
     */
    private final MainUI mainUiInstance;

    /**
     * Constructs the {@code Logic} object with references to the UI instances.
     *
     * @param baseUiInstance The {@code BaseUI} instance for display and history management.
     * @param mainUiInstance The {@code MainUI} instance for input and button handling.
     */
    Logic(BaseUI baseUiInstance, MainUI mainUiInstance) {
        this.baseUiInstance = baseUiInstance;
        this.mainUiInstance = mainUiInstance;
    }

    /**
     * Handles the button press events and performs the appropriate action based on the input.
     * <p>
     * It processes digits, operators, clear operations, and evaluates expressions.
     * </p>
     *
     * @param keyPressed The {@code ActionEvent} triggered by the button press.
     */
    protected void performAction(ActionEvent keyPressed) {
        mainUiInstance.inputString = keyPressed.getActionCommand();

        if (baseUiInstance.display.getText().equals("0")) {
            baseUiInstance.display.setText("");
        }

        switch (mainUiInstance.inputString) {
            case "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "00", ".":
                mainUiInstance.input.append(mainUiInstance.inputString);
                baseUiInstance.display.setText(baseUiInstance.display.getText() + mainUiInstance.inputString);
                break;
            case "+", "-", "*", "/", "%":
                mainUiInstance.input.append(" " + mainUiInstance.inputString + " ");
                baseUiInstance.display.setText(baseUiInstance.display.getText() + " " + mainUiInstance.inputString + " ");
                break;
            case "=":
                handleResult();
                break;
            case "C":
                if (mainUiInstance.input.length() > 1) {
                    mainUiInstance.input.deleteCharAt(mainUiInstance.input.length() - 1);
                    baseUiInstance.display.setText(mainUiInstance.input.toString());
                } else if (mainUiInstance.input.length() == 1) {
                    mainUiInstance.input.setLength(0);
                    baseUiInstance.display.setText("0");
                }
                break;
            case "CE":
                if (!baseUiInstance.display.getText().isEmpty()) {
                    mainUiInstance.input.setLength(0);
                    mainUiInstance.inputString = "";
                    baseUiInstance.display.setText("0");
                    baseUiInstance.history.setText("");
                }
                break;
        }
    }

    /**
     * Handles the evaluation of the expression and displays the result.
     * <p>
     * This method:
     * <ul>
     *     <li>Converts the infix expression to postfix using {@code InfixToPostfix} class.</li>
     *     <li>Evaluates the result and displays it.</li>
     *     <li>Updates the input and history.</li>
     * </ul>
     */
    private void handleResult() {
        try {
            baseUiInstance.history.setText(baseUiInstance.display.getText());

            new InfixToPostfix(mainUiInstance.input.toString(), mainUiInstance);

            baseUiInstance.display.setText(Double.toString(mainUiInstance.result));
            mainUiInstance.input.setLength(0);
            mainUiInstance.input.append(mainUiInstance.result);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
    }

}
