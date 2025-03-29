package com.project;

import com.github.lalyos.jfiglet.FigletFont;
import com.project.system.Menu;

/**
 * The {@code Main} class is the entry point for the Student-Course Registration System.
 * It displays a banner using JFiglet and initializes the system menu.
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class Main {

    /**
     * The main method that starts the application.
     * It displays a banner with the system's name and initializes the menu.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {

        try {
            String banner = FigletFont.convertOneLine("Student-Course Reg. Sys.");
            System.out.println(banner);

            new Menu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
