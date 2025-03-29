package com.project.calculator;

import java.util.List;
import java.util.Stack;

/**
 * The {@code PostfixEvaluation} class evaluates a mathematical expression
 * in postfix notation using a stack-based approach.
 * <p>
 * This class performs the following tasks:
 * </p>
 * <ul>
 *     <li>Processes postfix expressions containing operands and operators.</li>
 *     <li>Evaluates the expression using a stack.</li>
 *     <li>Updates the result in the {@code MainUI} instance.</li>
 * </ul>
 */
public class PostfixEvaluation {

    /**
     * Constructs a {@code PostfixEvaluation} object and evaluates the given postfix expression.
     * <p>
     * The result is stored in the {@code MainUI} instance for display.
     * </p>
     *
     * @param expression     The postfix expression to evaluate (as a {@code List<String>}).
     * @param mainUiInstance The {@code MainUI} instance to store the result.
     */
    PostfixEvaluation(List<String> expression, MainUI mainUiInstance) {
        mainUiInstance.result = evaluatePostfix(expression);
    }

    /**
     * Evaluates the given postfix expression.
     * <p>
     * The method uses a stack to:
     * </p>
     * <ul>
     *     <li>Push operands onto the stack.</li>
     *     <li>Pop two operands when an operator is encountered.</li>
     *     <li>Perform the operation and push the result back onto the stack.</li>
     * </ul>
     *
     * @param expression The postfix expression as a {@code List<String>}.
     * @return The result of the evaluated expression as a {@code double}.
     */
    private double evaluatePostfix(List<String> expression) {
        Stack<Double> stack = new Stack<>();

        for (String token : expression) {
            if (isOperator(token)) {
                double num2 = stack.pop();
                double num1 = stack.pop();
                double result = performOperation(num1, num2, token);
                stack.push(result);
            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop();
    }

    /**
     * Checks if the given token is a mathematical operator.
     * <p>
     * Supported operators: {@code +, -, *, /, %}.
     * </p>
     *
     * @param token The token to check.
     * @return {@code true} if the token is an operator, {@code false} otherwise.
     */
    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%");
    }

    /**
     * Performs a mathematical operation on two operands based on the given operator.
     * <p>
     * The supported operations include:
     * </p>
     * <ul>
     *     <li>{@code +} → Addition</li>
     *     <li>{@code -} → Subtraction</li>
     *     <li>{@code *} → Multiplication</li>
     *     <li>{@code /} → Division</li>
     *     <li>{@code %} → Modulus</li>
     * </ul>
     *
     * @param num1     The first operand.
     * @param num2     The second operand.
     * @param operator The mathematical operator as a {@code String}.
     * @return The result of the operation as a {@code double}.
     */
    public static double performOperation(double num1, double num2, String operator) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            case "%" -> num1 % num2;
            default -> 0;
        };
    }

}
