package com.applicationsupport.model;

import javax.swing.ImageIcon;

import com.applicationsupport.constants.Constants;
import com.applicationsupport.images.Image;
import com.applicationsupport.images.ImageFactory;

public class Laser extends Sprite {
	
	public Laser() {
		
	}
	
	public Laser(int x,int y) {
		this.x = x;
		this.y = y;
		initialize();
	}

	private void initialize() {
		ImageIcon imageIcon = ImageFactory.createImage(Image.LASER);
		setImage(imageIcon.getImage());
		
		setX(x+Constants.SPACESHIP_WIDTH/2);
		setY(y);
		
	}

	@Override
	public void move() {


		this.y -= Constants.LASER_HORIZONTAL_SPEED;
		
		if(this.y<5) {
			this.die();
		}
	}

}
