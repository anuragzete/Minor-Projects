package com.application.quizApplication;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Main class for the Quiz Application.
 * <p>
 * This application creates a GUI-based quiz using Java Swing.
 * It displays questions and allows the user to interact with the quiz.
 * </p>
 *
 * @author Anurag Zete
 * @version 1.0.0
 */
public class Application extends JFrame {

    /**
     * Constructs the Quiz Application window.
     * <p>
     * Sets the default window properties such as:
     * </p>
     * <ul>
     *     <li>Title: "Quiz Application"</li>
     *     <li>Size: 500x400 pixels</li>
     *     <li>Centered on the screen</li>
     *     <li>Non-resizable</li>
     * </ul>
     * <p>
     * Adds the {@link ApplicationPanel} component to display the quiz content.
     * </p>
     */
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

    /**
     * The entry point of the Quiz Application.
     * <p>
     * Initializes the GUI on the Event Dispatch Thread (EDT)
     * using {@code SwingUtilities.invokeLater()} for thread safety.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Application::new);
    }
}
