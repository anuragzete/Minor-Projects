package com.project;

import com.github.lalyos.jfiglet.FigletFont;

import java.util.Scanner;

public class GradeCalculator {
    private static Scanner sc = new Scanner(System.in);
    private static double totalMarks = 0;
    private static int totalSubjects = 0;
    private static double averageMarks = 0;

    private enum Grades {
        A(91, 100),
        B(81, 90),
        C(71, 80),
        D(61, 70),
        E(51, 60);

        final double minMarks;
        final double maxMarks;

        Grades(double minMarks, double maxMarks) {
            this.minMarks = minMarks;
            this.maxMarks = maxMarks;
        }

        static Grades getGrade() {
            for (Grades grade : Grades.values()) {
                if (grade.minMarks < averageMarks && grade.maxMarks > averageMarks) {
                    return grade;
                }
            }
            return null;
        }
    }

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
