package com.application.quizApplication;


import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

/**
 * The {@code ApplicationPanel} class is responsible for rendering the quiz interface
 * and handling the game logic, including:
 * <ul>
 *     <li>Displaying questions with multiple-choice options.</li>
 *     <li>Tracking the player's score.</li>
 *     <li>Managing a countdown timer for each question.</li>
 * </ul>
 * <p>
 * This panel uses Swing components to create a GUI-based quiz.
 * </p>
 */
public class ApplicationPanel extends JPanel {

    /** Label for displaying the countdown timer. */
    protected JLabel timerLabel;

    /** Text area for displaying the current question. */
    protected JTextArea questionLabel;

    /** Array of radio buttons for the multiple-choice options. */
    protected JRadioButton[] options;

    /** Grouping radio buttons to allow only one selection at a time. */
    protected ButtonGroup group;

    /** Button for moving to the next question. */
    protected JButton nextButton;

    /** Current question index. */
    protected int currentQuestion = 0;

    /** Player's score. */
    protected int score = 0;

    /** Time left in seconds for the current question. */
    protected int timeLeft = 30;

    /** Timer for countdown functionality. */
    protected Timer timer;

    /** Panel to hold and display the options. */
    protected JPanel optionsPanel;

    /** 2D array containing questions, options, and correct answers. */
    protected String[][] questions = {
            {"What is the capital of France?", "Paris", "London", "Berlin", "Madrid", "Paris"},
            {"Which language is used for Android development?", "Python", "Swift", "Java", "C++", "Java"},
            {"Who developed Java?", "Microsoft", "Sun Microsystems", "Google", "Apple", "Sun Microsystems"},
            {"What is the largest planet in our solar system?", "Earth", "Mars", "Jupiter", "Venus", "Jupiter"},
            {"Which data structure follows LIFO?", "Queue", "Stack", "Linked List", "Array", "Stack"},
            {"Who wrote 'To Kill a Mockingbird'?", "Harper Lee", "J.K. Rowling", "Mark Twain", "Jane Austen", "Harper Lee"},
            {"Which is the longest river in the world?", "Amazon", "Nile", "Mississippi", "Yangtze", "Nile"},
            {"What does HTTP stand for?", "Hyper Transfer Text Protocol", "HyperText Transfer Protocol", "Hyperlink Transfer Protocol", "Hyper Transfer Terminal Protocol", "HyperText Transfer Protocol"},
            {"Which programming language is known as the mother of all languages?", "C", "Python", "Java", "Assembly", "C"},
            {"Which gas do plants absorb from the atmosphere?", "Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen", "Carbon Dioxide"}
    };

    /**
     * Constructs the quiz panel, initializes the GUI layout, and loads the first question.
     * <p>
     * Sets up the main layout, timer label, question area, options, and the next button.
     * </p>
     */
    ApplicationPanel() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        timerLabel = new JLabel("Time Left: " + timeLeft + "s", JLabel.RIGHT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(Color.RED);
        headerPanel.add(timerLabel, BorderLayout.EAST);

        questionLabel = new JTextArea();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);
        questionLabel.setOpaque(false);
        questionLabel.setEditable(false);
        questionLabel.setFocusable(false);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        headerPanel.add(questionLabel, BorderLayout.SOUTH);

        this.add(headerPanel, BorderLayout.NORTH);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        options = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 16));
            options[i].setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            group.add(options[i]);
            optionsPanel.add(options[i]);
        }
        this.add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextButton.setFocusPainted(false);
        nextButton.setBackground(new Color(0, 122, 255));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        nextButton.addActionListener(e -> {
            checkAnswer();
            loadNextQuestion();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); 
        buttonPanel.add(nextButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        loadNextQuestion();
    }

    /**
     * Loads the next question into the GUI.
     * <p>
     * Updates the question label, sets the options, and starts the timer.
     * If the quiz is over, displays the final score using {@code JOptionPane}.
     * </p>
     */
    protected void loadNextQuestion() {
        if (currentQuestion >= questions.length) {
            JOptionPane.showMessageDialog(this, "Quiz Over! Your Score: " + score);
            return;
        }

        questionLabel.setText("Q" + (currentQuestion + 1) + ": " + questions[currentQuestion][0]);

        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[currentQuestion][i + 1]);
        }

        group.clearSelection();

        timeLeft = 30;
        timerLabel.setText("Time Left: " + timeLeft + "s");
        if (timer != null) {
            timer.stop();
        }
        startTimer();
    }

    /**
     * Starts the countdown timer for the current question.
     * <p>
     * Decreases the timer by one second every tick. When the timer reaches zero:
     * </p>
     * <ul>
     *     <li>Automatically checks the answer.</li>
     *     <li>Loads the next question.</li>
     * </ul>
     */
    protected void startTimer() {
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + "s");

            if (timeLeft == 0) {
                timer.stop();

                checkAnswer();

                if (currentQuestion == questions.length) {
                    JOptionPane.showMessageDialog(ApplicationPanel.this, "Time's up!");
                }

                loadNextQuestion();
            }
        });
        timer.start();
    }

    /**
     * Checks the selected answer against the correct answer.
     * <p>
     * Increases the score if the answer is correct.
     * Moves to the next question.
     * </p>
     */
    protected void checkAnswer() {
        if (timer != null) {
            timer.stop();
        }
        for (JRadioButton option : options) {
            if (option.isSelected() && option.getText().equals(questions[currentQuestion][5])) {
                score++;
                break;
            }
        }
        currentQuestion++;
    }
}
