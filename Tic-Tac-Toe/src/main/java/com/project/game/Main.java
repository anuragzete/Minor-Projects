package com.project.game;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Scanner;

/**
 * The {@code Main} class serves as the entry point for the Tic-Tac-Toe game.
 * <p>
 * It initializes the game, handles user input, and allows the player to replay
 * or exit the game.
 * </p>
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class Main {

    /**
     * The main method initializes the Tic-Tac-Toe game.
     * <p>
     * It displays a banner, starts the game, and handles the replay logic.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String banner = FigletFont.convertOneLine("Tic-Tac-Toe Game");
            System.out.println(banner);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while (true){
            try {
                new TicTacToe();
                System.out.println("Want to play again? (y/n)");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                } else if (answer.equalsIgnoreCase("n")) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    throw new Exception("Please,Enter a valid input.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        sc.close();
    }
}