package com.application.quizApplication;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Application extends JFrame {

    public Application() {
        super("Quiz Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        ApplicationPanel quizPanel = new ApplicationPanel();
        this.add(quizPanel);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Application::new);
    }
}
