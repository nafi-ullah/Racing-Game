package com.nafimridul.gameframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestPanel {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 700;
    private static final int RECTANGLE_WIDTH = 100;
    private static final int RECTANGLE_HEIGHT = 200;
    private static final int NUM_RECTANGLES = 3;
    private static final int DELAY_MS = 30; // Adjust the delay for the animation speed

    List<ImageIcon> carImages;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Moving Rectangles Randomly");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(null); // We'll set the positions of rectangles manually

            List<JPanel> rectangles = new ArrayList<>();

            for (int i = 0; i < NUM_RECTANGLES; i++) {
                JPanel bluePanel = createBluePanel();
                mainPanel.add(bluePanel);
                rectangles.add(bluePanel);
            }

            positionRectanglesRandomly(rectangles, mainPanel);

            frame.add(mainPanel);
            frame.pack();
            frame.setVisible(true);
            

            startAnimation(rectangles, mainPanel);
        });
    }

    private static void positionRectanglesRandomly(List<JPanel> rectangles, JPanel mainPanel) {
        Random random = new Random();
        for (JPanel rectangle : rectangles) {
            int x = random.nextInt(FRAME_WIDTH - RECTANGLE_WIDTH);
            int y = random.nextInt(FRAME_HEIGHT - RECTANGLE_HEIGHT);
            rectangle.setBounds(x, y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        }
    }

    private static void startAnimation(List<JPanel> rectangles, JPanel mainPanel) {
        Timer timer = new Timer(DELAY_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                for (JPanel rectangle : rectangles) {
                    int y = rectangle.getY() + 1; // Move down by 1 pixel
                    if (y > FRAME_HEIGHT) {
                        y = -RECTANGLE_HEIGHT; // Reset to the top if off the screen
                        int x = random.nextInt(FRAME_WIDTH - RECTANGLE_WIDTH);
                        rectangle.setLocation(x, y);
                    }
                    rectangle.setLocation(rectangle.getX(), y);
                }
            }
        });
        timer.start();
    }

    private static JPanel createBluePanel() {
        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);
        bluePanel.setPreferredSize(new Dimension(RECTANGLE_WIDTH, RECTANGLE_HEIGHT));
        return bluePanel;
    }
}
