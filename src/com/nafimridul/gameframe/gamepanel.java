package com.nafimridul.gameframe;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class gamepanel extends JPanel implements ActionListener{

//	static final int SCREEN_WIDTH = 1300;
//	static final int SCREEN_HEIGHT = 750;
//	static final int UNIT_SIZE = 50;
//	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
//	static final int DELAY = 175;
//	final int x[] = new int[GAME_UNITS];
//	final int y[] = new int[GAME_UNITS];
//	int bodyParts = 6;
//	int applesEaten;
//	int appleX;
//	int appleY;
//	char direction = 'R';
	boolean running = false;
//	Timer timer;
	
	
	//car game variables
	
	private int xpos = 300; //x position of the car
    private int ypos = 700;//y position of the car
    private ImageIcon car, car1, car2, car3; // car image

    Random random = new Random(); //random number generator
    private int num1 = 400; //x position of the obstacles
    private int tree1ypos = 400, tree2ypos = -200, tree3ypos = -500, tree4ypos = 100, tree5ypos = -300, tree6ypos = 500;
    private int roadmove = 0; //y position of the road
    private int carxpos[] = {100, 200, 300, 400, 500}; //x positions
    private int carypos[] = {-240, -480, -720, -960, -1200}; //y positions
    private int cxpos1 = 0, cxpos2 = 2, cxpos3 = 4;//x position of the car
    private int cypos1 = random.nextInt(5), cypos2 = random.nextInt(5), cypos3 = random.nextInt(5);
    //y positions
    int y1pos = carypos[cypos1];
    int y2pos = carypos[cypos2];
    int y3pos = carypos[cypos3]; //actual y positions of the car
    //y position
    int x1pos=carxpos[cxpos1];
    int x2pos=carxpos[cxpos2];
    int x3pos=carxpos[cxpos3];
    int score = 0; //score by default
    int delay = 100; //delay to move the cars step by step
    int speed = 60; //speed of the car
    private ImageIcon tree1, tree2, tree3; //tree images

    private boolean gameover = false, paint = false;
    
	gamepanel(){
		random = new Random();
		//this.setPreferredSize(new Dimension(1000,300));
		this.setBounds(300, 10, 700, 700);
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		//newApple();
		running = true;
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		if(running) {
			/*
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			*/
//			g.setColor(Color.red);
//			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
//		
//			for(int i = 0; i< bodyParts;i++) {
//				if(i == 0) {
//					g.setColor(Color.green);
//					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
//				}
//				else {
//					g.setColor(new Color(45,180,0));
//					//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
//					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
//				}			
//			}
//			g.setColor(Color.red);
//			g.setFont( new Font("Ink Free",Font.BOLD, 40));
//			FontMetrics metrics = getFontMetrics(g.getFont());
//			g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
//		}
//		else {
//			gameOver(g);
//		}
			g.setColor(new Color(28, 84, 31)); //set the color of grass
	        g.fillRect(0,0,700,700); //draw the grass
	        g.setColor(new Color(71,72,76)); //color of the road
	        g.fillRect(90,0,10,700);
	        g.fillRect(600,0,10,700);
	        g.fillRect(100,0,500,700);

	        //let's draw the lines
	        if(roadmove==0){
	            for(int i=0;i<=700;i+=100){
	                g.setColor(Color.white);
	                g.fillRect(350,i,10,70);
	            }
	            roadmove=1;
	        }
	        //draw the road lines again for next frame
	        else if(roadmove==1){
	            for(int i=50;i<=700;i+=100){
	                g.setColor(Color.white);
	                g.fillRect(350,i,10,70);
	            }
	            roadmove=0; //set the roadmove to 0
	        }
	        try{
	            tree1=new ImageIcon(ImageIO.read(getClass().getClassLoader()
	            .getResourceAsStream("images/tree.png")));
	            tree2=new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/tree.png")));
	            tree3=new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/tree.png")));

	        }catch(IOException e){
	            e.printStackTrace();
	        }

	        tree1.paintIcon(this,g,0,tree1ypos);
	        num1=random.nextInt(500);//generate random number
	        tree1ypos+=50;//increment the position by 50
	        tree2.paintIcon(this,g,0,tree2ypos);
	        num1=random.nextInt(500);
	        tree2ypos+=50; //increment the position by 50
	        tree3.paintIcon(this,g,0,tree3ypos);
	        tree3ypos+=50;
	        tree1.paintIcon(this,g,600,tree4ypos);
	        tree4ypos+=50;
	        tree2.paintIcon(this,g,600,tree5ypos);
	        tree5ypos+=50;
	        tree3.paintIcon(this,g,600,tree6ypos);
	        tree6ypos+=50;
	        if(tree1ypos>700){
	            num1=random.nextInt(500);
	            tree1ypos=-num1;//reset the position for tree

	        }
	        if(tree2ypos>700){
	          num1=random.nextInt(500);
	          tree2ypos=-num1;
	        }
	        if(tree3ypos>700){
	            num1=random.nextInt(500);
	            tree3ypos=-num1;
	        }
	        if(tree4ypos>700){
	            num1=random.nextInt(500);
	            tree4ypos=-num1;

	        }
	        if(tree5ypos>700){
	            num1=random.nextInt(500);
	            tree5ypos=-num1;
	        }
	        if(tree6ypos>700){
	            num1=random.nextInt(500);
	            tree6ypos=-num1;
	        }
	        //load image for car
	        try{
	            car=new ImageIcon(ImageIO.read(getClass().getClassLoader()
	            .getResourceAsStream("images/car1.png")));

	        }catch(IOException e){
	            e.printStackTrace();
	        }
	        car.paintIcon(this,g,xpos,ypos);//draw a car on my screen
	        ypos-=40;
	        if(ypos<500){
	            ypos=500;
	        }
	        //load the other cars
	        try{
	            car1=new ImageIcon(ImageIO.read(getClass().getClassLoader()
	            .getResourceAsStream("images/car2.png")));
	            car2=new ImageIcon(ImageIO.read(getClass().getClassLoader()
	            .getResourceAsStream("images/car3.png")));
	            car3=new ImageIcon(ImageIO.read(getClass().getClassLoader()
	            .getResourceAsStream("images/car4.png")));


	        }catch(IOException e){
	            e.printStackTrace();
	        }
	        car1.paintIcon(this,g,x1pos,y1pos);
	        car2.paintIcon(this,g,x2pos,y2pos);
	        car3.paintIcon(this,g,x3pos,y3pos);
	        y1pos+=50;
	        y2pos+=50;
	        y3pos+=50;
	        // car will move out of the screen so reset the position
	        if(y1pos>700){
	            cxpos1=random.nextInt(5);
	            cypos1=random.nextInt(5);
	            y1pos=carypos[cypos1]; // reset the y position of first opposite car

	        }
	        if(y2pos>700){
	            cxpos2++;
	            if(cxpos2>4){
	                cxpos2=0;
	            }
	            cxpos2=random.nextInt(5);
	            cypos1=random.nextInt(5);
	            y2pos=carypos[cypos2];
	        }
	        if(y3pos>700){
	            cxpos3++;
	            if(cxpos3>4){
	                cxpos3=0;
	            }
	            cxpos3=random.nextInt(5);
	            cypos3=random.nextInt(5);
	            y3pos=carypos[cypos3];
	        }
	        if(cxpos1==cxpos2 && cypos1>-100 && cypos2>-100){
	            cxpos1-=1;
	            if(cxpos1<0){
	                cxpos1+=2;
	            }
	        }
	        if(cxpos1==cxpos3 && cypos1>-100 && cypos3>-100){
	            cxpos3-=1;
	            if(cxpos3<0){
	                cxpos3+=2;
	            }
	        }
	        if(cxpos2==cxpos3 && cypos2>-100 && cypos3>-100){
	            cxpos2-=1;
	            if(cxpos2<0){
	                cxpos2+=2;
	            }
	        }
	        //more logic
	        if(cxpos1<2 && cxpos2<2 && cxpos3<2){
	            if(cxpos1==0 && cxpos2==0 && cxpos3==1){
	                cxpos3++;
	                cxpos2++;
	            }
	            else if(cxpos1==0 && cxpos2==1 && cxpos3==0){
	                cxpos3++;
	                cxpos2++;
	            }
	            else if(cxpos1==1 && cxpos2==0 && cxpos3==0){
	                cxpos1++;
	                cxpos2++;
	            }
	        }
	        //let's put the logic for gameover
	        if(y1pos<ypos && y1pos+175>ypos && x1pos==xpos){
	            gameover=true;
	        }
	        if(y2pos<ypos && y2pos+175>ypos && x2pos==xpos){
	            gameover=true;
	        }
	        if(y3pos<ypos && y3pos+175>ypos && x3pos==xpos){
	            gameover=true;
	        }
	        if(ypos<y1pos && y1pos+175>y1pos && x1pos==xpos){
	            gameover=true;
	        }
	        if(ypos<y2pos && y2pos+175>y2pos && x2pos==xpos){
	            gameover=true;
	        }
	        if(ypos<y3pos && y3pos+175>y3pos && x3pos==xpos){
	            gameover=true;
	        }
	        if(gameover){
	            g.setColor(Color.CYAN);
	            g.fillRect(120,210,460,200);
	            g.setColor(Color.DARK_GRAY);
	            g.setFont(new Font("New Times Roman",Font.BOLD,50));
	            g.setColor(Color.red);
	            g.drawString("Game Over  !",210,270);
	            g.setColor(Color.white);
	            g.setFont(new Font("New Times Roman",Font.BOLD,30));
	            g.drawString("Press Enter to Restart",190,340);
	            if(!paint){
	                repaint();
	                paint=true;
	            }
	        }
	        else{
	            repaint();
	        }
	        //score
	        g.setColor(Color.blue);//border of rect
	        g.fillRect(120,35,220,50);
	        g.setColor(Color.black);
	        g.fillRect(125,40,210,40);//actual rect where to display the score
	        //speed
	        g.setColor(Color.blue);//border of rect
	        g.fillRect(385,35,180,50);
	        g.setColor(Color.black);
	        g.fillRect(390,40,170,40);
	        g.setColor(Color.white);
	        g.setFont(new Font("New Times Roman",Font.BOLD,30));
	        g.drawString("Score :"+score,130,67);
	        g.drawString(speed+" km/h",400,67);
	        score++;
	        speed++;
	        if(speed>140){
	            speed=240-delay;
	        }
	        if(score%50==0){
	            delay-=10;
	            if(delay<60){
	              delay=60;//reset the delay to 60
	            }
	        }
	        try{
	            TimeUnit.MICROSECONDS.sleep(delay);

	        } catch(InterruptedException e){
	            e.printStackTrace();
	        }
	        }
		
	}
	
//	public void gameOver(Graphics g) {
//		//Score
//		g.setColor(Color.red);
//		g.setFont( new Font("Ink Free",Font.BOLD, 40));
//		FontMetrics metrics1 = getFontMetrics(g.getFont());
//		g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
//		//Game Over text
//		g.setColor(Color.red);
//		g.setFont( new Font("Ink Free",Font.BOLD, 75));
//		FontMetrics metrics2 = getFontMetrics(g.getFont());
//		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		if(running) {
//			move();
//			checkApple();
//			checkCollisions();
//		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_LEFT && !gameover){
	            //if the left key is pressed then move the car in left
	            xpos-=100;
	            if(xpos<100){
	                xpos=100; //set my car to the left most position
	            }
	        }
	        if(e.getKeyCode()==KeyEvent.VK_RIGHT && !gameover){
	            //if the right key is pressed then move the car in right
	            xpos+=100;
	            if(xpos>500){
	                xpos=500; //set my car to the right most position
	            }
	        }
	        if(e.getKeyCode()==KeyEvent.VK_ENTER && gameover){
	            //if the gameover then restart the game
	            gameover=false;
	            paint=false;
	            cxpos1=0;
	            cxpos2=2;
	            cxpos3=4;
	            cypos1=random.nextInt(5);
	            cypos2=random.nextInt(5);
	            cypos3=random.nextInt(5);
	            y1pos=carypos[cypos1];
	            y2pos=carypos[cypos2];
	            y3pos=carypos[cypos3];
	            x1pos=carxpos[cxpos1];
	            x2pos=carxpos[cxpos2];
	            x3pos=carxpos[cxpos3];
	            speed=90;
	            score=0;
	            delay=100;
	            xpos=300;
	            ypos=700;
	            repaint();//restart of my game

		}
	}
}
}