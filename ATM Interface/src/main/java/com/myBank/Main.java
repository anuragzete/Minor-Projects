
package com.myBank;

import com.github.lalyos.jfiglet.FigletFont;

/**
 * The {@code Main} class serves as the entry point for the ATM Interface application.
 * <p>
 * It displays a banner using the <a href="https://github.com/lalyos/jfiglet">JFiglet</a> library
 * and initializes the {@link ATM} interface.
 * </p>
 *
 * <h2>Usage:</h2>
 * <pre>
 *     java com.myBank.Main
 * </pre>
 *
 * <h2>Dependencies:</h2>
 * - {@link com.github.lalyos.jfiglet.FigletFont}: Used for rendering ASCII art banners.
 * - {@link ATM}: Represents the core functionality of the ATM interface.
 *
 * <h2>Exception Handling:</h2>
 * - Catches and prints any exceptions that occur during banner rendering or ATM initialization.
 *
 * @author Anurag Zete
 * @version 1.0.0
 * @since 2025-01-24
 */
public class Main {

    /**
     * The entry point of the ATM application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        try {
            String banner = FigletFont.convertOneLine("ATM Interface");

            System.out.println(banner);
            new ATM();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


