
import java.util.Scanner;

public class TicTacToe {
    private boolean isGameOver = false;
    private int turns = 0;
    private final String playerX = "X";
    private final String playerO = "O";
    private String currentPlayer;
    private final String[][] board = new String[3][3];

    private Scanner sc = new Scanner(System.in);

    TicTacToe() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = "";
            }
        }
        System.out.println("Tic Tac Toe");
        System.out.println("============");
        selectPlayer();
        initializeBoard();
        playGame();
    }

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

    private void setWinner() {
        System.out.println(currentPlayer + " is the Winner!");
        System.out.println("==================================");
    }
}
