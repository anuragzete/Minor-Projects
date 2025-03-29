package com.project;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Random;
import java.util.Scanner;

/**
 * The {@code PassGenerator} class generates random passwords based on user-selected criteria:
 * <ul>
 *     <li>Password length.</li>
 *     <li>Inclusion of numbers, uppercase, lowercase, and special characters.</li>
 * </ul>
 * <p>
 * The program uses a {@link Random} instance to select random characters from the chosen character sets.
 * It displays the generated password and allows the user to generate multiple passwords in a loop.
 * </p>
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class PassGenerator {

    /** Flag indicating if numbers should be included. */
    private static boolean isNumbersIncluded;

    /** Flag indicating if uppercase letters should be included. */
    private static boolean isUppercaseLettersIncluded;

    /** Flag indicating if lowercase letters should be included. */
    private static boolean isLowercaseLettersIncluded;

    /** Flag indicating if special characters should be included. */
    private static boolean isSpecialCharactersIncluded;

    /** Set of numeric characters (0-9). */
    private static final String numbers = "0123456789";

    /** Set of lowercase alphabetic characters (a-z). */
    private static final String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";

    /** Set of uppercase alphabetic characters (A-Z). */
    private static final String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** Set of special characters. */
    private static final String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

    /**
     * The main entry point for the password generator application.
     * <p>
     * Displays a banner, prompts the user for password criteria, generates a password, and
     * allows multiple password generations in a loop.
     * </p>
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        try {
            String banner = FigletFont.convertOneLine("Pass Generator");
            System.out.println(banner);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        while (true) {
            int length;
            StringBuilder charSet = new StringBuilder();
            StringBuilder password = new StringBuilder();

            try {
                System.out.println("Enter the desired length of password: ");
                length = sc.nextInt();
                sc.nextLine();

            } catch (Exception e) {
                System.out.println("Invalid Input, please try again\n");
                sc.nextLine();
                continue;
            }

            promptUser(sc);
            buildCharSet(charSet);

            if (charSet.isEmpty()) {
                System.out.println("No character sets selected, Please select at least one character set.");
                continue;
            }
            for (int i = 0; i < length; i++) {
                int index = rand.nextInt(charSet.length());
                password.append(charSet.charAt(index));
            }

            System.out.println("Generated password : " + password);
            try {
                System.out.println("Want to Continue? (Y/N)");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("N")) {
                    System.out.println("Exiting Program!");
                    sc.close();
                    break;
                } else if (input.equalsIgnoreCase("Y")) {}
                else {
                    throw new Exception();
                }
            }catch (Exception e) {
                System.out.println("Invalid Input, Please try again\n");
            }
        }
    }

    /**
     * Prompts the user to select character sets for password generation.
     * <p>
     * Asks whether to include:
     * <ul>
     *     <li>Numbers</li>
     *     <li>Uppercase letters</li>
     *     <li>Lowercase letters</li>
     *     <li>Special characters</li>
     * </ul>
     *
     * @param sc The {@link Scanner} object used for user input.
     */
        private static void promptUser(Scanner sc) {
            System.out.println("Want to include numbers ? (Y/N)");
            isNumbersIncluded = checkValidity(sc);

            System.out.println("Want to include uppercase letters ? (Y/N)");
            isUppercaseLettersIncluded = checkValidity(sc);

            System.out.println("Want to include lowercase letters ? (Y/N)");
            isLowercaseLettersIncluded = checkValidity(sc);

            System.out.println("Want to include special characters ? (Y/N)");
            isSpecialCharactersIncluded = checkValidity(sc);
        }

    /**
     * Builds the final character set for password generation.
     * <p>
     * Appends characters from the selected categories to the character set.
     * </p>
     *
     * @param charSet The {@link StringBuilder} to store the combined character set.
     */
    private static void buildCharSet(StringBuilder charSet) {
        if (isNumbersIncluded) {
            charSet.append(numbers);
        }
        if (isLowercaseLettersIncluded) {
            charSet.append(lowercaseLetters);
        }
        if (isUppercaseLettersIncluded) {
            charSet.append(uppercaseLetters);
        }
        if (isSpecialCharactersIncluded) {
            charSet.append(specialCharacters);
        }
    }

    /**
     * Validates the user's input (Y/N) for character set inclusion.
     *
     * @param sc The {@link Scanner} object used for input.
     * @return {@code true} if the user selects 'Y', otherwise {@code false}.
     */
    private static boolean checkValidity(Scanner sc) {
        while (true){
            try {
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("N")) {
                    return false;
                } else if (input.equalsIgnoreCase("Y")) {
                    return true;
                }
                else {
                    throw new Exception();
                }
            }catch (Exception e) {
                System.out.println("Invalid Input, Please try again");
            }
        }
    }
}