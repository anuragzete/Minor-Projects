
import java.util.Scanner;

public class PassStrengthChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Password Strength Checker");
        while (true) {
            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            String feedback = checkPasswordStrength(password);
            System.out.println(feedback);

            try {
                System.out.println("Want to check again? (Y/N)");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    continue;
                } else if (answer.equalsIgnoreCase("N")) {
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid choice, Please try again");
            }
        }
        System.out.println("Exiting...");
        sc.close();
    }

    private static String checkPasswordStrength(String password) {
        int length = password.length();
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        StringBuilder feedback = new StringBuilder();

        if (length < 8) {
            feedback.append("Password is too short (password must include least 8 characters).\n");
        } else {
            feedback.append("Password length is enough.\n");
        }

        if (!hasUppercase) {
            feedback.append("Password should have at least one uppercase letter.\n");
        } else {
            feedback.append("Password has an uppercase letters.\n");
        }

        if (!hasLowercase) {
            feedback.append("Password should have at least one lowercase letter.\n");
        } else {
            feedback.append("Password has a lowercase letters.\n");
        }

        if (!hasNumber) {
            feedback.append("Password should have at least one number.\n");
        } else {
            feedback.append("Password has a number.\n");
        }

        if (!hasSpecialChar) {
            feedback.append("Password should have at least one special character.\n");
        } else {
            feedback.append("Password has special characters.\n");
        }

        if (length >= 8 && hasUppercase && hasLowercase && hasNumber && hasSpecialChar) {
            feedback.append("Password is strong!");
        } else {
            feedback.append("Password could be stronger.");
        }

        return feedback.toString();
    }
}
