package com.project;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Scanner;

/**
 * Main class for the Palindrome Checker application.
 * <p>
 * This program checks if a given word or phrase is a palindrome.
 * It ignores spaces, punctuation, and case sensitivity while checking.
 * </p>
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class PalindromeChecker {

    /**
     * The entry point for the Palindrome Checker application.
     * <p>
     * Displays a welcome banner, prompts the user for input, and
     * checks whether the entered phrase is a palindrome. The program
     * loops until the user chooses to exit.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        boolean isPalindrome = true;
        Scanner input = new Scanner(System.in);

        try {
            String banner = FigletFont.convertOneLine("Palindrome Checker");
            System.out.println(banner);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        while (true) {
            System.out.println("Enter the word or phrase : ");
            String phrase = input.nextLine();

            String normalisedPhrase = phrase.replaceAll("[^a-zA-Z]", "").toLowerCase();

            for (int i = 0; i < (normalisedPhrase.length() / 2); i++) {
                if (normalisedPhrase.charAt(i) != normalisedPhrase.charAt((normalisedPhrase.length()) - i - 1)) {
                    isPalindrome = false;
                }
            }

            if (isPalindrome) {
                System.out.println("Given phrase is a palindrome.");
            }else {
                System.out.println("Given phrase is not a palindrome.");
            }

            if (checkContinuity(input)) {
            } else {
                System.out.println("Exiting program...");
                break;
            }
        }
        input.close();
    }

    /**
     * Asks the user whether they want to check another phrase.
     *
     * @param input The Scanner object used to read user input.
     * @return {@code true} if the user wants to continue, {@code false} otherwise.
     */
    private static boolean checkContinuity(Scanner input) {
        System.out.println("\nWant to check another phrase ? (Y/N)");
        String answer = input.nextLine();
        while (true) {
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Please enter a valid choice! ");
                input.next();
            }
        }
    }
}
