package com.project;

import com.github.lalyos.jfiglet.FigletFont;
import java.util.Scanner;

/**
 * Temperature Converter Application.
 * <p>
 * This program provides a console-based temperature conversion utility with the following functionalities:
 * <ul>
 *     <li>Convert Celsius to Fahrenheit</li>
 *     <li>Convert Fahrenheit to Celsius</li>
 *     <li>Exit the program</li>
 * </ul>
 * The program uses {@code jfiglet} to display a banner in ASCII art.
 *
 * @author Anurag Zete
 * @version 1.0.0
 * @since 2025-02-24
 */
public class TempConverter {

    /**
     * Default constructor for the {@code TempConverter} class.
     * <p>
     * This constructor is automatically invoked when creating an instance of the class.
     */
    public TempConverter() { }

    /**
     * The main entry point for the temperature converter program.
     * <p>
     * It displays a banner, provides a menu for the user to select conversion operations,
     * and handles invalid inputs gracefully.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int choice;
        String unit;
        double convertedTemperature;
        Scanner input = new Scanner(System.in);

        try {
            String banner = FigletFont.convertOneLine("Temperature Converter");
            System.out.println(banner);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        while (true) {
            try {
                System.out.println("Choose the Conversion - \n1] Celsius to Fahrenheit \n2] Fahrenheit to Celsius \n3] Exit");
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter Temperature in Celsius: ");
                        unit = "fahrenheit";
                        convertedTemperature = celsiusToFahrenheit(input.nextDouble());
                        break;

                    case 2:
                        System.out.println("Enter Temperature in Fahrenheit: ");
                        unit = "celsius";
                        convertedTemperature = fahrenheitToCelsius(input.nextDouble());
                        break;
                    case 3:
                        System.out.println("Exiting Program!!!");
                        input.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                        continue;
                }
                System.out.printf("Temperature in %s : %.2f \n\n", unit, convertedTemperature);

            } catch (Exception e) {
                System.out.println("Invalid input");
                input.next();
            }
        }

    }

    /**
     * Converts temperature from Celsius to Fahrenheit.
     *
     * @param celsius The temperature in Celsius.
     * @return The equivalent temperature in Fahrenheit.
     */
    private static double celsiusToFahrenheit(double celsius) {
        return ((celsius * 9 / 5) + 32);
    }

    /**
     * Converts temperature from Fahrenheit to Celsius.
     *
     * @param fahrenheit The temperature in Fahrenheit.
     * @return The equivalent temperature in Celsius.
     */
    private static double fahrenheitToCelsius(double fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }
}
