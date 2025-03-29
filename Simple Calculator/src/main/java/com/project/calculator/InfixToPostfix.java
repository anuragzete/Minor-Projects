package com.project.calculator;

import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code InfixToPostfix} class is responsible for converting an infix expression
 * to postfix notation and triggering the postfix evaluation process.
 * <p>
 * This class performs the following tasks:
 * </p>
 * <ul>
 *     <li>Converts an infix expression into postfix notation.</li>
 *     <li>Handles operator precedence and parentheses.</li>
 *     <li>Triggers the {@code PostfixEvaluation} class to calculate the result.</li>
 * </ul>
 */
public class InfixToPostfix {

    /**
     * Constructs an {@code InfixToPostfix} object and performs infix to postfix conversion.
     * <p>
     * This constructor:
     * <ul>
     *     <li>Converts the provided infix expression to postfix.</li>
     *     <li>Initializes the {@code PostfixEvaluation} to evaluate the result.</li>
     * </ul>
     *
     * @param infix         The infix expression to be converted.
     * @param mainUiInstance The {@code MainUI} instance for result handling.
     */
        InfixToPostfix(String infix, MainUI mainUiInstance) {
            new PostfixEvaluation(infixToPostfixConversion(infix),mainUiInstance);
        }

    /**
     * Converts the given infix expression into postfix notation.
     * <p>
     * The conversion is based on the standard infix-to-postfix algorithm:
     * <ul>
     *     <li>Operands are added directly to the postfix list.</li>
     *     <li>Operators are pushed to a stack while respecting precedence.</li>
     *     <li>Parentheses are handled properly to maintain order of operations.</li>
     * </ul>
     *
     * @param infix The infix expression as a {@code String}.
     * @return A {@code List<String>} representing the postfix expression.
     */
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

    /**
     * Checks if the given token is an operand (numeric value).
     * <p>
     * An operand is any valid number. This method uses {@code Double.parseDouble}
     * to determine whether the token is numeric.
     * </p>
     *
     * @param token The token to be checked.
     * @return {@code true} if the token is an operand, {@code false} otherwise.
     */
        private boolean isOperand(String token) {
            try {
                Double.parseDouble(token);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

    /**
     * Checks if the given token is a mathematical operator.
     * <p>
     * Supported operators: {@code +, -, *, /, %, ^}
     * </p>
     *
     * @param token The token to be checked.
     * @return {@code true} if the token is an operator, {@code false} otherwise.
     */
        private boolean isOperator(String token) {
            return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%") || token.equals("^");
        }

    /**
     * Checks the precedence of the operator.
     * <p>
     * Supported operators: {@code +, -, *, /, %, ^}
     * </p>
     *
     * @param operator The operator to be checked.
     * @return {@code int} based on the operator.
     */
        private int precedence(String operator) {
            return switch (operator) {
                case "+", "-" -> 1;
                case "*", "/", "%" -> 2;
                case "^" -> 3;
                default -> -1;
            };
        }
}
