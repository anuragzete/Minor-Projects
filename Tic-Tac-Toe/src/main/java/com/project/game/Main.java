package com.project.game;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Scanner;

public class Main {
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