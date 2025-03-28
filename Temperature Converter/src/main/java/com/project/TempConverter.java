package com.project;

import com.github.lalyos.jfiglet.FigletFont;
import java.util.Scanner;

public class TempConverter {
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

    private static double celsiusToFahrenheit(double celsius) {
        return ((celsius * 9 / 5) + 32);
    }

    private static double fahrenheitToCelsius(double fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }
}
