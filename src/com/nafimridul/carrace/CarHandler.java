package com.nafimridul.carrace;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CarHandler implements KeyListener{
	CarGameGui g;
	
	public CarHandler(CarGameGui ref)
	{
		this.g = ref;
	}
	
		@Override
	    public void keyTyped(KeyEvent e) {

	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	    	int code = e.getKeyCode();
	    	//System.out.println(code);
	    	int x = g.carPanel.getX();
    		int y= g.carPanel.getY();
	    	if(code==37 ) {
	    		
	    		
	    		if(x>200 ) {
	    			g.carPanel.setLocation(x-137,y);
	    		}
	    		
	    		//g.carPanel.setLocation(x-g.speed,y);
	    	}
	    	else if(code==39) {
	    		
	    		int width = g.fr.getWidth();
	    	//	int carWidth = g.carPanel.getWidth();
	    		if(x <= 260)
	    		{
	    		g.carPanel.setLocation(x+137,y);
	    		}
	    	}
	    	
	    //	System.out.println(x + "  " + y);
	        
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {

	    }
	    
	

}
