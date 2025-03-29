package com.project;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Scanner;

/**
 * The {@code GradeCalculator} class calculates the grades of students based on their marks.
 * <p>
 * It takes input for the number of subjects, accepts marks for each subject,
 * calculates the total and average marks, and assigns a grade based on predefined ranges.
 * </p>
 *
 * <p>
 * The program also handles invalid inputs and allows the user to calculate grades repeatedly
 * until they choose to exit.
 * </p>
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class GradeCalculator {

    /**
     * Scanner object for reading user input.
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Stores the cumulative total marks of all subjects.
     */
    private static double totalMarks = 0;

    /**
     * Stores the total number of subjects entered by the user.
     */
    private static int totalSubjects = 0;

    /**
     * Stores the calculated average marks.
     */
    private static double averageMarks = 0;

    /**
     * Enum representing the grade system based on marks ranges.
     * <p>
     * Each grade has a minimum and maximum mark range.
     * </p>
     */
    private enum Grades {
        /**
         * Grade A: 91 - 100 marks.
         */
        A(91, 100),

        /**
         * Grade B: 81 - 90 marks.
         */
        B(81, 90),

        /**
         * Grade C: 71 - 80 marks.
         */
        C(71, 80),

        /**
         * Grade D: 61 - 70 marks.
         */
        D(61, 70),

        /**
         * Grade E: 51 - 60 marks.
         */
        E(51, 60);

        /**
         * The minimum marks required for the grade.
         */
        final double minMarks;

        /**
         * The maximum marks allowed for the grade.
         */
        final double maxMarks;

        /**
         * Constructor to initialize the grade range.
         *
         * @param minMarks The minimum marks required for the grade.
         * @param maxMarks The maximum marks allowed for the grade.
         */
        Grades(double minMarks, double maxMarks) {
            this.minMarks = minMarks;
            this.maxMarks = maxMarks;
        }

        /**
         * Determines the grade based on the calculated average marks.
         *
         * @return The grade corresponding to the average marks, or {@code null} if no grade matches.
         */
        static Grades getGrade() {
            for (Grades grade : Grades.values()) {
                if (grade.minMarks < averageMarks && grade.maxMarks > averageMarks) {
                    return grade;
                }
            }
            return null;
        }
    }

    /**
     * The main method to run the grade calculator program.
     * <p>
     * It displays a banner, starts the grade calculation loop,
     * and exits the program when the user chooses to stop.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            String banner = FigletFont.convertOneLine("Student Grade Calculator");
            System.out.println(banner);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        program();

        System.out.println("Exiting program!");
        sc.close();
    }

    /**
     * Runs the grade calculation program loop.
     * <p>
     * - Prompts the user for the number of subjects. <br>
     * - Calls the {@code calculateGrade()} method to accept marks. <br>
     * - Displays the result using {@code displayResult()}. <br>
     * - Allows the user to repeat the grade calculation or exit.
     * </p>
     */
    private static void program() {
        while (true) {
            try {
                System.out.println("Enter total number of subjects :");
                totalSubjects = sc.nextInt();
                sc.nextLine();

                totalMarks = calculateGrade(sc, totalSubjects);

                displayResult();

                System.out.println("Want to calculate grade's again ? (Y/N)");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("N")) {
                    break;
                } else if (answer.equalsIgnoreCase("Y")) {
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid input,Please try again");
                sc.nextLine();
            }
        }
    }

    /**
     * Accepts marks for each subject and calculates the total marks.
     * <p>
     * - Marks are stored in an array for validation purposes. <br>
     * - Accepts "ab" or empty input as an indicator of absence (assigns 0.0). <br>
     * - Ensures valid marks range (0 - 100) and handles incorrect input formats.
     * </p>
     *
     * @param sc            The {@code Scanner} object for user input.
     * @param totalSubjects The total number of subjects.
     * @return The cumulative total marks.
     */
    private static double calculateGrade(Scanner sc, int totalSubjects) {
        double[] marks = new double[totalSubjects];
        double total = 0.0;
        for (int i = 0; i < totalSubjects; i++) {
            while (true) {
                System.out.println("Enter marks for subject " + (i + 1) + ":");
                String input = sc.nextLine();

                if (input.isEmpty() || input.equalsIgnoreCase("ab")) {
                    marks[i] = 0.0;
                    break;
                } else {
                    try {
                        double mark = Double.parseDouble(input);

                        if (mark < 0 || mark > 100) {
                            System.out.println("Marks must be between 0 and 100");
                            continue;
                        }
                        marks[i] = mark;
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid marks, please try again");
                    }
                }

            }

            total += marks[i];
        }
        return total;
    }

    /**
     * Displays the final results, including:
     * <ul>
     *     <li>Total marks obtained out of the maximum possible marks.</li>
     *     <li>Average marks calculated.</li>
     *     <li>The grade obtained based on the average.</li>
     * </ul>
     */
    private static void displayResult() {
        averageMarks = totalMarks / totalSubjects;
        Grades grade = Grades.getGrade();

        System.out.printf("Total marks out of %d : %.2f\nAverage marks : %.2f\nGrade obtained : %s\n", (totalSubjects * 100), totalMarks, averageMarks, grade);

        for (int i = 0; i < 35; i++) {
            System.out.print("=");
        }
        System.out.println();
    }
}
