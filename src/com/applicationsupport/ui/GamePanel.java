package com.applicationsupport.ui;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.applicationsupport.constants.Constants;
import com.applicationsupport.images.Image;
import com.applicationsupport.images.ImageFactory;

public class GamePanel extends JPanel {
	

	private ImageIcon backgroundImage;
	private Timer timer; 
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
	}
	private void initializeVariables() {
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
		this.timer = new Timer(Constants.GAME_SPPED, new GameLoop(this));
		this.timer.restart();
	}
	public void initializeLayout() {
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH,Constants.BOARD_HEIGHT));
	}
	
	public void doOneLoop() {
		update();
		repaint();
	}
	
	public void update() {
		System.out.println("update");
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
	}
}
