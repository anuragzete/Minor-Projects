package com.project.game;

import java.util.Scanner;

    /**
    * The {@code TicTacToe} class represents the core logic of the Tic-Tac-Toe game.
    * <p>
    * It handles board initialization, player selection, game play, and winner determination.
    * </p>
    */
    public class TicTacToe {

    /**
     * Flag to indicate if the game is over.
     */
    private boolean isGameOver = false;

    /**
     * Counter to track the number of turns taken.
     * <p>
     * Maximum value is 9, as the board has 9 cells.
     * </p>
     */
    private int turns = 0;

    /**
     * Symbol representing Player X.
     */
    private final String playerX = "X";

    /**
     * Symbol representing Player O.
     */
    private final String playerO = "O";

    /**
     * The symbol of the player whose turn it is (either "X" or "O").
     */
    private String currentPlayer;

    /**
     * 3x3 game board represented as a 2D array.
     * Each cell contains either "X", "O", or an empty string.
     */
    private final String[][] board = new String[3][3];

    /**
     * Scanner object for reading user input.
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new TicTacToe game.
     * <p>
     * Initializes the board, prompts the user to select the first player,
     * and starts the game loop.
     * </p>
     */
    TicTacToe() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = "";
            }
        }
        selectPlayer();
        initializeBoard();
        playGame();
    }

    /**
     * Allows the user to select the first player (X or O).
     * <p>
     * Ensures that the input is valid and assigns the current player accordingly.
     * </p>
     * */
    private void selectPlayer() {
        int userInput;

        while (true) {
            while (true) {
                try {
                    System.out.println("Select first player X or O\n1] Player X\n2] Player O");
                    userInput = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    sc.nextLine();
                }
            }

            if (userInput == 1) {
                currentPlayer = playerX;
                break;
            } else if (userInput == 2) {
                currentPlayer = playerO;
                break;
            } else {
                System.out.println("Please enter a valid option");
            }
        }
    }

    /**
     * Displays the initial board layout with cell numbers for reference.
     * <p>
     * The board is printed with cell numbers 1-9, arranged in a 3x3 grid format.
     * </p>
     */
    private void initializeBoard() {
        int idx = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + idx + " ");
                idx++;
                if (j < 2) {
                    System.out.print("|");
                }
                if (i < 2 && j == 2) {
                    System.out.println("\n---+---+---");
                }
            }
        }
        System.out.println("\n-------------");
    }

    /**
     * Controls the main game loop.
     * <p>
     * Handles player turns, board updates, and checks for a winner or a tie.
     * Switches players after every valid move.
     * </p>
     */
    private void playGame() {
        int userInput;

        while (true) {
            if (!isGameOver) {
                int row;
                int col;

                System.out.println("Player " + currentPlayer + "'s turn");
                while (true) {
                    try {
                        System.out.print("Select cell number :");
                        userInput = sc.nextInt();
                        break;
                    }catch (Exception e) {
                        System.out.println("Invalid input, please try again");
                        sc.nextLine();
                    }
                }

                if (userInput > 0 && userInput < 10) {
                    row = (userInput - 1) / 3;
                    col = (userInput - 1) % 3;
                } else {
                    System.out.println("Sorry, out of bound input");
                    continue;
                }

                if (board[row][col].isEmpty()) {
                    updateBoard(row, col);
                    turns++;
                    checkWinner();
                    if (!isGameOver) {
                        currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                    }
                } else {
                    System.out.println("Sorry, invalid cell number");
                }
            }else {
                break;
            }
        }
    }

    /**
     * Updates the board with the current player's move and redraws the board.
     *
     * @param row The row index of the board (0-2).
     * @param col The column index of the board (0-2).
     */
    private void updateBoard(int row, int col) {
        board[row][col] = currentPlayer;
        int idx = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].isEmpty()) {
                    System.out.print(" " + idx + " ");
                } else {
                    System.out.print(" " + board[i][j] + " ");
                }
                idx++;
                if (j < 2) {
                    System.out.print("|");
                }
                if (i < 2 && j == 2) {
                    System.out.println("\n---+---+---");
                }
            }
        }
        System.out.println("\n-------------");
    }

    /**
     * Checks for a winner or a tie by evaluating board conditions.
     * <p>
     * - Checks for winning combinations horizontally, vertically, and diagonally.<br>
     * - Declares the winner or tie and terminates the game if the conditions are met.
     * </p>
     */
    private void checkWinner() {
        //horizontal check
        for (int i = 0; i < 3; i++) {
            if (board[i][0].isEmpty()) continue;
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) {
                isGameOver = true;
                setWinner();
            }
        }

        //vertical check
        for (int i = 0; i < 3; i++) {
            if (board[0][i].isEmpty()) continue;
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])) {
                isGameOver = true;
                setWinner();
            }
        }

        //diagonal check
        if (!(board[0][0].isEmpty() || board[0][2].isEmpty())) {
            if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) ||
                    board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
                isGameOver = true;
                setWinner();
            }
        }

        //tie check
        if (turns == 9) {
            isGameOver = true;
            System.out.println("It's a tie!");
            System.out.println("==================================");
        }
    }

    /**
     * Declares the current player as the winner and ends the game.
     */
    private void setWinner() {
        System.out.println(currentPlayer + " is the Winner!");
        System.out.println("==================================");
    }
}
