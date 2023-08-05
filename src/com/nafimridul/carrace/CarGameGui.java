package com.nafimridul.carrace;
import java.awt.Color;
import java.awt.Rectangle;
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
	
	 Set<Integer> numbers = new HashSet<>();
//	List<ImageIcon> carImages;
	List<JLabel> cars;
	
	JPanel[] carPanels;
	JPanel[] carPanelsRight;
	
	JLabel[] carImages;
	JLabel changeImage;
	
	String[] carImagePaths = {
            "resources/images/car4.png",
            "resources/images/car2.png",
            "resources/images/car3.png",
            "resources/images/car5.png",
            "resources/images/car6.png"
        };
	
	ArrayList<Integer> carpos;
	
	

	
	public CarGameGui() {
		speed = 20;
		width = 600;
		height = 800;
		hnd = new CarHandler(this);
	//	carImages = new ArrayList<>();
		cars = new ArrayList<>();
		carpos = new ArrayList<>();
		
		 
		
		
	
		
		carpos.add(115);
		carpos.add(255);
		carpos.add(390);
		
		//loadCarImages();
		
		initGui();
		
		
	}
	
	
	
	private void initGui()
	{

		


		fr = new JFrame("Car Racing Game");
        fr.setLayout(null);

        // Setting up the background label
        background = new JLabel("", new ImageIcon("resources/images/backgif.gif"), JLabel.CENTER);
        background.setBounds(0, 0, width, height);
        fr.add(background);

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
        
       
       
        
        
        // Set up a timer to update car positions and repaint the GUI
        
        timer = new Timer(speed, e -> {
        	boolean left = false;
        	Random randompos = new Random();
            int indexpos = randompos.nextInt(5);
            
            
            
            // Move the cars vertically (change the y-coordinate)
            for (int i = 0; i < 3; i++) {
            	
            	
            	
            	
            	 JPanel car = carPanels[i];
//            	 JLabel imagecar;
//            	 imagecar= carImages[i];
            	 if(car.getBounds().intersects(carPanel.getBounds())) {
             		System.out.println("GameOver");
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
                    System.out.println(indexi);
                    numbers.add(indexi);
                    int s = numbers.size();
                    if(s==3) {
                    	List<Integer> numberList = new ArrayList<>(numbers);
                    	int firstNumber = numberList.get(0);
                    	indexi = firstNumber;
                    	numbers.clear();
                    	continue;
                    }
                    
                    int posci = carpos.get(indexi);
                    
//                    if (checkCollision(car, carPanel)) {
//                        // Game over logic here (e.g., display a message or stop the game timer)
//                        //timer.stop();
//                        System.out.println("Game Over!");
//                    }
                    
      
                    
                    carPanels[i].setLocation(posci,  i*520);

        
                }

               car.setLocation(car.getX(), newY);
              
                       
            }
            carPanel.repaint();
            
            
            
           
            
            // OpCarPanel.repaint();
        });
        timer.start();
        
        
       
        

        fr.setSize(width, height);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
        fr.addKeyListener(hnd);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
