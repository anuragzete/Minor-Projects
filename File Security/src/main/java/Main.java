
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static JFrame frame = new JFrame();

    private static String password;
    private static File file;
    private static boolean isEncryptSelected;

    Main(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        System.out.println("Welcome,Choose the operation");
        fileHandler();

        System.out.println("Exiting...");
        frame.dispose();
        sc.close();
    }

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
