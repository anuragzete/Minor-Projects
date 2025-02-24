package com.project.calculator;

import java.util.List;
import java.util.Stack;

public class PostfixEvaluation {

    PostfixEvaluation(List<String> expression, MainUI mainUiInstance) {
        mainUiInstance.result = evaluatePostfix(expression);
    }

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

    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%");
    }

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
