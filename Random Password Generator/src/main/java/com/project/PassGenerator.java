package com.project;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Random;
import java.util.Scanner;

public class PassGenerator {
    private static boolean isNumbersIncluded;
    private static boolean isUppercaseLettersIncluded;
    private static boolean isLowercaseLettersIncluded;
    private static boolean isSpecialCharactersIncluded;

    private static final String numbers = "0123456789";
    private static final String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private static final String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

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