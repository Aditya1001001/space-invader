package com.applicationsupport.model;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.applicationsupport.constants.Constants;
import com.applicationsupport.images.Image;
import com.applicationsupport.images.ImageFactory;

public class SpaceShip extends Sprite{
	
	public SpaceShip() {
		initialize();
	}
	
	public void initialize() {
		
		ImageIcon imageIcon = ImageFactory.createImage(Image.SPACESHIP);
		setImage(imageIcon.getImage());
		
		int start_x = Constants.BOARD_WIDTH/2-Constants.SPACESHIP_WIDTH/2;
		int start_y = Constants.BOARD_HEIGHT-100;
		
		setX(start_x);
		setY(start_y);
	}

	@Override
	public void move() {
		x+=dx;
		
		
		//bound checking
		if(x<Constants.BORDER_PADDING) {
			x=Constants.BORDER_PADDING;
		}
		if (x>Constants.BOARD_WIDTH-2*Constants.BORDER_PADDING)
			x=Constants.BOARD_WIDTH-2*Constants.BORDER_PADDING;
	}

	public void keyReleased(KeyEvent e) {
		
		int key =e.getKeyCode();
		
		if(key==KeyEvent.VK_LEFT) {
			dx = 0;
		}
		
		if(key==KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		
	}

	public void keyPressed(KeyEvent e) {
		
		int key =e.getKeyCode();
		
		if(key==KeyEvent.VK_LEFT) {
			dx = -2;
		}
		
		if(key==KeyEvent.VK_RIGHT) {
			dx = 2;
		}
		
	}

}
