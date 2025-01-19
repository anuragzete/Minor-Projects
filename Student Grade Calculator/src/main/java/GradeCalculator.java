
import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Student Grade Calculator");

        while (true) {
            try {
                System.out.println("Enter total number of grades :");
                int totalGrades = sc.nextInt();
                sc.nextLine();

                double total = calculateGrade(sc,totalGrades);

                System.out.printf("Average grade is %.3f\n", (total / totalGrades));

                System.out.println("Want to calculate grade's again ? (Y/N)");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("N")) {
                    break;
                } else if (answer.equalsIgnoreCase("Y")) { } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid input,Please try again");
                sc.nextLine();
            }
        }
        System.out.println("Exiting program!");
        sc.close();
    }

    private static double calculateGrade(Scanner sc,int totalGrades) {
        double[] grades = new double[totalGrades];
        double total = 0.0;
        for (int i = 0; i < totalGrades; i++) {
            while (true) {
                System.out.println("Enter grade " + (i + 1) + ":");
                String input = sc.nextLine();

                if (input.isEmpty() || input.equalsIgnoreCase("ab")) {
                    grades[i] = 0.0;
                    break;
                } else {
                    try {
                        grades[i] = Double.parseDouble(input);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid grade");
                    }
                }
            }

            total += grades[i];
        }
        return total;
    }
}
