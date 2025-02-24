package com.project.calculator;

import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class InfixToPostfix {

        InfixToPostfix(String infix, MainUI mainUiInstance) {
            new PostfixEvaluation(infixToPostfixConversion(infix),mainUiInstance);
        }

        private List<String> infixToPostfixConversion(String infix) {
            String[] tokens = infix.split(" ");
            Stack<String> stack = new Stack<>();
            List<String> postfix = new ArrayList<>();

            for (String token : tokens) {
                if (isOperand(token)) {
                    postfix.add(token);
                } else if (isOperator(token)) {
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                        postfix.add(stack.pop());
                    }
                    stack.push(token);
                } else if (token.equals("(")) {
                    stack.push(token);
                } else if (token.equals(")")) {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                    }
                    stack.pop(); // Remove the left parenthesis
                }
            }

            while (!stack.isEmpty()) {
                postfix.add(stack.pop());
            }

            return postfix;
        }

        private boolean isOperand(String token) {
            try {
                Double.parseDouble(token);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private boolean isOperator(String token) {
            return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%") || token.equals("^");
        }

        private int precedence(String operator) {
            return switch (operator) {
                case "+", "-" -> 1;
                case "*", "/", "%" -> 2;
                case "^" -> 3;
                default -> -1;
            };
        }

}
