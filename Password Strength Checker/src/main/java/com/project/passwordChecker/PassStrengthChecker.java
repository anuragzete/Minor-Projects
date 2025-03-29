package com.project.passwordChecker;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Scanner;

/**
 * Main class for the Password Strength Checker application.
 * <p>
 * This program checks the strength of a given password by evaluating
 * its length, uppercase and lowercase letters, numbers, and special characters.
 * It provides feedback on the password's strength.
 * </p>
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class PassStrengthChecker {

    /**
     * The entry point of the Password Strength Checker application.
     * <p>
     * Displays a welcome banner, prompts the user for passwords, and evaluates
     * their strength based on multiple conditions. The program runs in a loop
     * until the user chooses to exit.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String banner = FigletFont.convertOneLine("Pass Strength Checker");
            System.out.println(banner);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            String feedback = checkPasswordStrength(password);
            System.out.println(feedback);

            try {
                System.out.println("Want to check again? (Y/N)");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    continue;
                } else if (answer.equalsIgnoreCase("N")) {
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid choice, Please try again");
            }
        }
        System.out.println("Exiting...");
        sc.close();
    }

    /**
     * Checks the strength of the given password based on the following criteria:
     * <ul>
     *     <li>Length (at least 8 characters)</li>
     *     <li>Contains at least one uppercase letter</li>
     *     <li>Contains at least one lowercase letter</li>
     *     <li>Contains at least one number</li>
     *     <li>Contains at least one special character</li>
     * </ul>
     *
     * @param password The password to evaluate.
     * @return A string describing the password's strength and suggestions for improvement.
     */
    private static String checkPasswordStrength(String password) {
        int length = password.length();
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        StringBuilder feedback = new StringBuilder();

        if (length < 8) {
            feedback.append("Password is too short (password must include least 8 characters).\n");
        } else {
            feedback.append("Password length is enough.\n");
        }

        if (!hasUppercase) {
            feedback.append("Password should have at least one uppercase letter.\n");
        } else {
            feedback.append("Password has an uppercase letters.\n");
        }

        if (!hasLowercase) {
            feedback.append("Password should have at least one lowercase letter.\n");
        } else {
            feedback.append("Password has a lowercase letters.\n");
        }

        if (!hasNumber) {
            feedback.append("Password should have at least one number.\n");
        } else {
            feedback.append("Password has a number.\n");
        }

        if (!hasSpecialChar) {
            feedback.append("Password should have at least one special character.\n");
        } else {
            feedback.append("Password has special characters.\n");
        }

        if (length >= 8 && hasUppercase && hasLowercase && hasNumber && hasSpecialChar) {
            feedback.append("Password is strong!");
        } else {
            feedback.append("Password could be stronger.");
        }

        return feedback.toString();
    }
}
