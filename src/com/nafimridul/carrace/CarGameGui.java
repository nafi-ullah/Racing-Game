package com.nafimridul.carrace;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.html.HTMLDocument.Iterator;
//import java.util.Timer;

public class CarGameGui {
	JFrame fr;
	JPanel carPanel;
	JPanel invisiblecspace;
	CarHandler hnd;
	int speed;
	JLabel background;
	int width;
	int height;
	int posit=0;
	Timer timer;
	int score=0;
	private Timer timerInt;
	private int secRem;
	
	
	 ArrayList<Integer> numbers;
//	List<ImageIcon> carImages;
	List<JLabel> cars;
	
	JPanel[] carPanels;
	JPanel[] carPanelsRight;
	
	JLabel[] carImages;
	JLabel changeImage;
	JLabel  scoreLabel;
	
	
	
	ArrayList<Integer> carpos;
	
	JPanel scorePanel;
    
	String[] carImagePaths = {
            "resources/images/car4.png",
            "resources/images/car2.png",
            "resources/images/car3.png",
            "resources/images/car5.png",
            "resources/images/car6.png"
        };
	

	
	public CarGameGui() {
		speed = 20;
		width = 600;
		height = 800;
		hnd = new CarHandler(this);
	//	carImages = new ArrayList<>();
		cars = new ArrayList<>();
		carpos = new ArrayList<>();
		numbers = new ArrayList<>();
		
	
		
		carpos.add(115);
		carpos.add(255);
		carpos.add(390);
		carpos.add(1000);
		
		//loadCarImages();
		
		initGui();
		
		
		
	}
	
	private void GameOver(int s) {
		
        GameOverWindow gameOverWindow = new GameOverWindow(s);
        gameOverWindow.setVisible(true);
      
        gameOverWindow.closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();// Close the window
            }
        });
        gameOverWindow.restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOverWindow.dispose();
                initGui();
                // Close the window
            }
        });
        
        
	}
	
	private void StartGame() {
		
		
		  timer = new Timer(speed, e -> {
	        	score++;
	        	scoreLabel.setText(
	        			"<html><div style='border: 2px solid black; padding: 5px; background-color: green;'>" +
	        	                  "Score: <br>" + score + "</div></html>");
	        	
	        	
	        	boolean left = false;
	        	Random randompos = new Random();
	            int indexpos = randompos.nextInt(5);
	            
	            
	            
	            // Move the cars vertically (change the y-coordinate)
	            for (int i = 0; i < 3; i++) {
	            	
	            	int prev= 0;
	            	
	            	
	            	 JPanel car = carPanels[i];
//	            	 JLabel imagecar;
//	            	 imagecar= carImages[i];
	            	 if(car.getBounds().intersects(carPanel.getBounds())) {
	             		System.out.println("GameOver");
	             		timer.stop();
	             		GameOver(score);
	            	 }
	            	
	                int newY = car.getY() + 5; // Adjust the movement speed as needed
	               //int OpY = OpCarPanel.getY() + 1;

	                if (newY > height) {
	                	
	                	carPanels[i].remove(carImages[i]);
	                	carImages[i] = new JLabel(new ImageIcon(carImagePaths[indexpos]));
	                    carImages[i].setBounds(0, 0, 100, 210);
	                    carPanels[i].add(carImages[i]);
	                	
	                    newY = -car.getHeight() ;
	                   
	                    carPanels[i].add(carImages[i]);
	                    carPanels[i].repaint();
	                    
	                    Random randi = new Random();
	                    int indexi = randi.nextInt(3);
	                    
	                    
	                    if(numbers.size()>3 && (indexi == 0 || indexi == 2)) {
	                    	Integer lastElement = numbers.get(numbers.size() - 1);
	                    	Integer PrevEl = numbers.get(numbers.size() - 2);
	                    	
	                    	if(indexi == 2 && lastElement == 1 && PrevEl <= 1 ) {
		                    	indexi = 0;
		                    	//System.out.println("Yes");	
		                    }
	                    	else if (indexi == 0 && lastElement == 1 && (PrevEl == 2 || PrevEl == 1)) {
	                    		indexi = 2;
	                    	}
	                    	
	                    }
	                    numbers.add(indexi);
	                    
	                   
	                    
	                    
	                   // System.out.println(indexi);
	                    int posci = carpos.get(indexi);
	                    
	      
	                    
	                    carPanels[i].setLocation(posci,  i*520);

	        
	                }

	               car.setLocation(car.getX(), newY);
	              
	                       
	            }
	            carPanel.repaint();
	            

	        });
	        timer.start();
	        
	}
	
	private void IntroWindow() {
		
		background = new JLabel("", new ImageIcon("resources/images/IntroGif.gif"), JLabel.CENTER);
        background.setBounds(0, 0, width, height);
        fr.add(background);
		
	}
	
	
	private void GameWindow() {
		

        
        scorePanel = new JPanel();
        scoreLabel = new JLabel("<html><body style='border: 2px solid black; padding: 5px;'>Score " + score + "</body></html>");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scorePanel.setSize(85,150);
        scorePanel.setOpaque(false);
        //scorePanel.setBackground(Color.GREEN);
        //scorePanel.setLayout(null);
        scorePanel.add(scoreLabel);
        scorePanel.setLocation(0,500);
        background.add(scorePanel);

        
        // Setting up the car panel
        carPanel = new JPanel();
        carPanel.setSize(100, 202);
        carPanel.setOpaque(false);
        carPanel.setLayout(null); // Set the layout to null for manual positioning
        carPanel.setLocation(255, 550);
        
        background.add(carPanel);
        
        
      
        
     // Adding the car image to the car panel
        JLabel mycar = new JLabel(new ImageIcon("resources/images/car1.png"));
        mycar.setBounds(0, 0, 100, 210);
        carPanel.add(mycar);
        
        
       //opposite car pannels
        carPanels = new JPanel[carImagePaths.length];
        carImages = new JLabel[carImagePaths.length];
        
        
       
        
        for (int i = 0; i < carImages.length; i++) {
            carPanels[i] = new JPanel();
            carPanels[i].setSize(100, 202);
            carPanels[i].setOpaque(false);
            carPanels[i].setLayout(null);
            
            
            Random randompos = new Random();
            int indexpos = randompos.nextInt(2);
            int posc = carpos.get(indexpos);
            
            
            
            
            
            carPanels[i].setLocation(120,  i*520); // Adjust vertical positioning
            background.add(carPanels[i]);
           // System.out.println(posc);
            carImages[i] = new JLabel(new ImageIcon(carImagePaths[i]));
            carImages[i].setBounds(0, 0, 100, 210);
            carPanels[i].add(carImages[i]);
            
        }
        
		
		
		
	}

	
	
	
	private void initGui()
	{

		


		fr = new JFrame("Car Racing Game");
        fr.setLayout(null);

        // intro show
        
        
	     secRem= 4;
	     
	     background = new JLabel("", new ImageIcon("resources/images/IntroGif.gif"), JLabel.CENTER);
	        background.setBounds(0, 0, width, height);
	        fr.add(background);
        
        timerInt = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secRem--;
                if (secRem >= 0) {       
                    
                    System.out.println("Intro Showing");
                } else {
                    timerInt.stop();
                    System.out.println("Show off");
                    fr.remove(background);
                    background = new JLabel("", new ImageIcon("resources/images/backgif.gif"), JLabel.CENTER);
                    background.setBounds(0, 0, width, height);
                    fr.add(background);
                    GameWindow();
                    StartGame();// Clear the image
                }
            }
        });

        timerInt.start();
        
        

        fr.setSize(width, height);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
        fr.addKeyListener(hnd);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
