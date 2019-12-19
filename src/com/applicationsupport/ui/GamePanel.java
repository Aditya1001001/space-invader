package com.applicationsupport.ui;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.applicationsupport.callbacks.GameEventListener;
import com.applicationsupport.constants.Constants;
import com.applicationsupport.images.Image;
import com.applicationsupport.images.ImageFactory;
import com.applicationsupport.model.SpaceShip;

public class GamePanel extends JPanel {
	

	private ImageIcon backgroundImage;
	private Timer timer; 
	private boolean inGame = true;
	private SpaceShip spaceShip;
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
	}
	private void initializeVariables() {
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
		this.spaceShip= new SpaceShip();
		this.timer = new Timer(Constants.GAME_SPPED, new GameLoop(this));
		this.timer.start();
		
	}
	public void initializeLayout() {
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH,Constants.BOARD_HEIGHT));
		setFocusable(true);
		addKeyListener(new GameEventListener(this));
	}
	
	public void doOneLoop() {
		update();
		repaint();
	}
	
	public void update() {
		this.spaceShip.move();
	}
	
	public void drawPlayer(Graphics g) {
		g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		doDrawing(g);
	}
	
	private void doDrawing(Graphics g) {
		if (inGame == true) {
			drawPlayer(g);
		} 
		else {
			if(timer.isRunning()) {
				timer.stop();
			}
		}
		Toolkit.getDefaultToolkit().sync();
		
	}
	public void keyReleased(KeyEvent e) {
		this.spaceShip.keyReleased( e);
	}
	public void keyPressed(KeyEvent e) {
		this.spaceShip.keyPressed( e);
		
	}
}
