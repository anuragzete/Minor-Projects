package com.project;

import com.github.lalyos.jfiglet.FigletFont;
import com.project.system.Menu;

public class Main {

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
