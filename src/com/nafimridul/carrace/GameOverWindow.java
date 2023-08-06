package com.nafimridul.carrace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverWindow extends JFrame {
    private JLabel gameOverLabel;
    private JLabel scoreLabel;
    public JButton closeButton;
    public JButton restartButton;

    public GameOverWindow(int score) {
        setTitle("Game Over");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
        restartButton = new JButton("Restart");

        

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        //JPanel RestartPanel = new JPanel();

        mainPanel.add(gameOverLabel, BorderLayout.CENTER);
        mainPanel.add(scoreLabel, BorderLayout.SOUTH);

        buttonPanel.add(closeButton);
        buttonPanel.add(restartButton);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        //RestartPanel.setLocation(0,200);
       // add(RestartPanel, BorderLayout.NORTH);
    }
}