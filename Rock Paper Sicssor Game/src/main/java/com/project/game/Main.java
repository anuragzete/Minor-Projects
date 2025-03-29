package com.project.game;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Random;
import java.util.Scanner;

/**
 * The {@code Main} class is the entry point for the Rock, Paper, Scissors game.
 * <p>
 * Features:
 * </p>
 * <ul>
 *     <li>Generates a random move for the computer.</li>
 *     <li>Compares the user's move with the computer's to determine the result.</li>
 *     <li>Displays the result and asks if the user wants to play again.</li>
 * </ul>
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class Main {

    /**
     * The main method to start the Rock, Paper, Scissors game.
     * <p>
     * It displays a banner, takes the user's move, generates the computer's move, and displays the result.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        try {
            String banner = FigletFont.convertOneLine("Rock,Paper,Scissors Game");
            System.out.println(banner);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        while (true){
            System.out.println("Choose between rock,paper and scissor: ");
            String userChoice = sc.next();
            if (!(userChoice.equalsIgnoreCase("rock") || userChoice.equalsIgnoreCase("paper") || userChoice.equalsIgnoreCase("scissor"))){
                System.out.println("Please enter a valid option");
                continue;
            }

            String[] choices = {"rock", "paper", "scissor"};
            String computerChoice = choices[rand.nextInt(3)];
            System.out.println("Computer choice: " + computerChoice);
            if (userChoice.equalsIgnoreCase(computerChoice)){
                System.out.println("It's a tie!");
            } else if (userChoice.equalsIgnoreCase("rock") && computerChoice.equalsIgnoreCase("scissor") || userChoice.equalsIgnoreCase("paper") && computerChoice.equalsIgnoreCase("rock") || userChoice.equalsIgnoreCase("scissor") && computerChoice.equalsIgnoreCase("paper")){
                System.out.println("You win!");
            } else {
                System.out.println("You lose!");
            }
            System.out.println("Do you want to play again? (y/n)");
            String playAgain = sc.next();
            while (true){
                if (playAgain.equalsIgnoreCase("n")){
                    System.out.println("Exiting ...");
                    sc.close();
                    System.exit(0);
                } else if (playAgain.equalsIgnoreCase("y")) {
                    break;
                }else {
                    System.out.println("Please, Enter a valid option");
                }
            }
        }

    }
}
