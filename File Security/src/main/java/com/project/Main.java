package com.project;

import com.github.lalyos.jfiglet.FigletFont;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.Scanner;

/**
 * The entry point for the File Security application.
 * <p>
 * This class provides a command-line interface for encrypting and
 * decrypting files. It uses Swing's {@link JFileChooser} for file selection
 * and handles user interactions with the console.
 *
 * @author Anurag Zete
 * @version 1.0.0
 * @since 2025-03-29
 */
public class Main {

    /** Scanner for reading user input. */
    private static Scanner sc = new Scanner(System.in);

    /** JFrame used for the file chooser dialog. */
    private static JFrame frame = new JFrame();

    /** The password used for encryption or decryption. */
    private static String password;

    /** The file selected by the user for encryption/decryption. */
    private static File file;

    /** Flag indicating whether encryption or decryption is selected. */
    private static boolean isEncryptSelected;

    /**
     * Constructor initializes the JFrame with the default close operation.
     */
    Main() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * The main method that starts the application.
     * <p>
     * It displays an ASCII banner, prompts the user to select an operation,
     * and handles the encryption or decryption process.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try{
            String banner = FigletFont.convertOneLine("File Security");
            System.out.println(banner);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Welcome,Choose the operation");
        fileHandler();

        System.out.println("Exiting...");
        frame.dispose();
        sc.close();
    }

    /**
     * Handles the file operations by allowing the user to choose between
     * encryption, decryption, or exiting the program.
     * <p>
     * Displays a menu and uses {@link JFileChooser} for file selection.
     * It repeatedly prompts the user until a valid operation is chosen.
     */
    private static void fileHandler(){
        while (true) {
            try {
                System.out.println("1] Encrypt the file \n2] Decrypt the file \n3] Exit");
                int choice = sc.nextInt();
                sc.nextLine();

                isEncryptSelected = (choice == 1)? true : false;
                if (choice == 3) {
                    return;
                } else {
                    try {
                        while (true) {
                            System.out.printf("Select the file for %s\n", (isEncryptSelected ? "encryption" : "decryption"));
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                            try {
                                if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                                    file = fileChooser.getSelectedFile();
                                    break;
                                } else {
                                    System.out.println("Sorry, file not selected\nPress enter Y to select file and N to exit.");
                                    String answer = sc.nextLine();
                                    try {
                                        if (answer.equalsIgnoreCase("Y")) {
                                        } else if (answer.equalsIgnoreCase("N")) {
                                            return;
                                        } else {
                                            throw new Exception();
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Please enter a valid option! (Y/N)");
                                    }
                                }
                            }catch (Exception e) {
                                System.out.println("Sorry, Unable to open File Chooser");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Please select a file!");
                        continue;
                    }

                    System.out.println("Enter the password");
                    password = sc.nextLine();
                    FileProcessing.fileEncryptDecrypt(file, password);
                    System.out.println("Want to encrypt/decrypt more files? (Y/N)");
                    String answer = sc.nextLine();
                    try {
                        if (answer.equalsIgnoreCase("Y")) {
                            continue;
                        } else if (answer.equalsIgnoreCase("N")) {
                            return;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter a valid option! (Y/N)");
                    }
                    break;
                }

            } catch (Exception e) {
                System.out.println("Invalid option, please try again");
            }
        }
    }
}
