package com.nafimridul.carracinggame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarGame extends JFrame {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int CAR_WIDTH = 50;
    private static final int CAR_HEIGHT = 100;

    private JPanel gamePanel;
    private int carX, carY;
    // Add enemy car positions as needed
    // private int enemy1X, enemy1Y;
    // private int enemy2X, enemy2Y;
    // private int enemy3X, enemy3Y;

    public CarGame() {
        initializeUI();
        initializeGame();
    }

    private void initializeUI() {
        setTitle("Car Racing Game");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };

        add(gamePanel);

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                repaint();
            }
        });

        timer.start();
    }

    private void initializeGame() {
        // Set the initial position of the car and enemy cars as needed
        carX = FRAME_WIDTH / 2 - CAR_WIDTH / 2;
        carY = FRAME_HEIGHT - CAR_HEIGHT - 20;
    }

    private void updateGame() {
        // Add logic for game updates here (e.g., move enemy cars, check for collisions, etc.)
    }

    private void drawGame(Graphics g) {
        // Draw the background image
        g.drawImage(getBackgroundImage(), 0, 0, this);

        // Draw the car and enemy cars
        g.drawImage(getCarImage(), carX, carY, this);
        // Draw enemy cars here (use enemyX and enemyY variables)

        // You can also draw other game elements like score, etc.
    }

    private Image getCarImage() {
        ImageIcon icon = new ImageIcon("resources/images/car1.png");
        return icon.getImage();
    }

    private Image getBackgroundImage() {
        ImageIcon icon = new ImageIcon("resources/images/background.png");
        return icon.getImage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarGame game = new CarGame();
            game.setVisible(true);
        });
    }
}
