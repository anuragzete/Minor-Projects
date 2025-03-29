package com.project.numberGuessGame;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Random;
import java.util.Scanner;

/**
 * Main class for the Number Guessing Game application.
 * <p>
 * This game randomly selects a number between 1 and 100, and the player
 * tries to guess it. The game keeps track of the player's score and
 * allows them to play multiple rounds.
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class Main {

    /**
     * The entry point for the Number Guessing Game.
     * <p>
     * Displays a welcome banner, handles the game loop, and manages the
     * player's score across multiple rounds.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        boolean playAgain = true;
        int score = 0;

        try {
            String banner = FigletFont.convertOneLine("Guess a Number");
            System.out.println(banner);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        while (playAgain) {
            int guess = rand.nextInt(100) + 1;
            int chances = 0;

            System.out.println("I think of a number, Guess the number between 1 to 100.");

            while (true) {
                if (chances > 9) {
                    System.out.println("You have reached the maximum number of chances. The number was " + guess);
                    break;
                } else {
                    System.out.print("Enter your guess: ");
                    int userGuess = sc.nextInt();
                    chances++;

                    if (userGuess == guess) {
                        System.out.println("Congratulations! You guessed the number in " + chances + " chances.");
                        score++;
                        break;
                    } else if (userGuess < guess) {
                        System.out.println("Try a higher number.");
                    } else {
                        System.out.println("Try a lower number.");
                    }
                }
            }

            if (score != 0) {
                System.out.println("Your score upto this round is " + score);
            }

            while (true) {
                System.out.println("Want to play again? (y/n)");
                String answer = sc.next();

                if (answer.equalsIgnoreCase("y")) {
                    break;
                } else if (answer.equalsIgnoreCase("n")) {
                    playAgain = false;
                    break;
                } else {
                    System.out.println("Please enter a valid input");
                }
            }
        }

        System.out.println("Exiting ...");
        sc.close();
    }
}
