package com.applicationsupport.model;

import javax.swing.ImageIcon;

import com.applicationsupport.images.Image;
import com.applicationsupport.images.ImageFactory;

public class EnemyShip extends Sprite {
	
	private boolean visible = true;
	
	public EnemyShip(int x, int y) {
		this.x=x;
		this.y=y;
		initialize();
	}

	private void initialize() {
		ImageIcon imageIcon = ImageFactory.createImage(Image.UFO);
		setImage(imageIcon.getImage());
		
	}
	
	

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void move(int direction) {
		this.x+=direction;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
