package com.applicationsupport.model;

import javax.swing.ImageIcon;

import com.applicationsupport.constants.Constants;
import com.applicationsupport.images.Image;
import com.applicationsupport.images.ImageFactory;

public class Bomb extends Sprite{
	
	public Bomb(int x,int y) {
		this.x=x;
		this.y=y;
		initialize();
	}

	private void initialize() {
		ImageIcon imageIcon = ImageFactory.createImage(Image.BOMB);
		setImage(imageIcon.getImage());
		
	}

	@Override
	public void move() {
		this.y++;
		
		if(y >= Constants.BOARD_HEIGHT - Constants.BOMB_HEIGHT) {
			die();
		}
		
	}

}
