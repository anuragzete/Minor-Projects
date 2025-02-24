package com.project.calculator;

import java.awt.event.ActionEvent;

public class Logic {
    private final BaseUI baseUiInstance;
    private final MainUI mainUiInstance;

    Logic(BaseUI baseUiInstance, MainUI mainUiInstance) {
        this.baseUiInstance = baseUiInstance;
        this.mainUiInstance = mainUiInstance;
    }

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
