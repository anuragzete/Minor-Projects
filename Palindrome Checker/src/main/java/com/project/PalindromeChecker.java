package com.project;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Scanner;

public class PalindromeChecker {
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
