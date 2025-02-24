package com.project.calculator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEvents implements KeyListener {
    private final BaseUI baseUiInstance;
    private final MainUI mainUiInstance;

    KeyboardEvents(BaseUI baseUiInstance, MainUI mainUiInstance) {
        this.baseUiInstance = baseUiInstance;
        this.mainUiInstance = mainUiInstance;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();

        if (baseUiInstance.display.getText().equals("0")) {
            baseUiInstance.display.setText("");
        }

        if (e.isShiftDown()) {
            switch (keyCode) {
                case KeyEvent.VK_EQUALS:
                    mainUiInstance.input.append(" + ");
                    baseUiInstance.display.setText(baseUiInstance.display.getText() + " + ");
                    break;
                case KeyEvent.VK_8:
                    mainUiInstance.input.append(" * ");
                    baseUiInstance.display.setText(baseUiInstance.display.getText() + " * ");
                    break;
                case KeyEvent.VK_5:
                    mainUiInstance.input.append(" % ");
                    baseUiInstance.display.setText(baseUiInstance.display.getText() + " % ");
                    break;
            }
        } else {
            switch (keyCode) {
                case KeyEvent.VK_PERIOD:
                case KeyEvent.VK_0:
                case KeyEvent.VK_1:
                case KeyEvent.VK_2:
                case KeyEvent.VK_3:
                case KeyEvent.VK_4:
                case KeyEvent.VK_5:
                case KeyEvent.VK_6:
                case KeyEvent.VK_7:
                case KeyEvent.VK_8:
                case KeyEvent.VK_9:
                case KeyEvent.VK_DECIMAL:
                case KeyEvent.VK_NUMPAD0:
                case KeyEvent.VK_NUMPAD1:
                case KeyEvent.VK_NUMPAD2:
                case KeyEvent.VK_NUMPAD3:
                case KeyEvent.VK_NUMPAD4:
                case KeyEvent.VK_NUMPAD5:
                case KeyEvent.VK_NUMPAD6:
                case KeyEvent.VK_NUMPAD7:
                case KeyEvent.VK_NUMPAD8:
                case KeyEvent.VK_NUMPAD9:
                    mainUiInstance.input.append(keyChar);
                    baseUiInstance.display.setText(baseUiInstance.display.getText() + keyChar);
                    break;
                case KeyEvent.VK_ADD:
                case KeyEvent.VK_SUBTRACT:
                case KeyEvent.VK_MINUS:
                case KeyEvent.VK_MULTIPLY:
                case KeyEvent.VK_DIVIDE:
                case KeyEvent.VK_SLASH:
                    mainUiInstance.input.append(" " + keyChar + " ");
                    baseUiInstance.display.setText(baseUiInstance.display.getText() + " " + keyChar + " ");
                    break;
                case KeyEvent.VK_ENTER:
                case KeyEvent.VK_SEPARATER:
                    handleResult();
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    if (mainUiInstance.input.length() > 1) {
                        mainUiInstance.input.deleteCharAt(mainUiInstance.input.length() - 1);
                        baseUiInstance.display.setText(mainUiInstance.input.toString());
                    } else if (mainUiInstance.input.length() == 1) {
                        mainUiInstance.input.setLength(0);
                        baseUiInstance.display.setText("0");
                    }
                    break;
                case KeyEvent.VK_DELETE:
                    if (!baseUiInstance.display.getText().isEmpty()) {
                        mainUiInstance.input.setLength(0);
                        baseUiInstance.display.setText("0");
                        baseUiInstance.history.setText("");
                    }
                    break;

                case KeyEvent.VK_ESCAPE:
                    baseUiInstance.dispose();
                    System.exit(0);
                    break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
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
