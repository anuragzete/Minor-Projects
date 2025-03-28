
package com.myBank;

import com.github.lalyos.jfiglet.FigletFont;

public class Main {
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


