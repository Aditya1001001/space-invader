package com.applicationsupport.ui;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.applicationsupport.callbacks.GameEventListener;
import com.applicationsupport.constants.Constants;
import com.applicationsupport.images.Image;
import com.applicationsupport.images.ImageFactory;
import com.applicationsupport.model.EnemyShip;
import com.applicationsupport.model.Laser;
import com.applicationsupport.model.SpaceShip;

public class GamePanel extends JPanel {
	

	private ImageIcon backgroundImage;
	private Timer timer; 
	private boolean inGame = true;
	private SpaceShip spaceShip;
	private Laser laser;
	private int direction =-1;
	private List<EnemyShip> enemyShips;
	
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
		initializeGame();
	}
	private void initializeGame() {
		for(int i=0;i<Constants.ENEMY_SHIPS_ROW;i++) {
			for(int j=0;j<Constants.ENEMY_SHIPS_COLUMN;j++) {
				EnemyShip enemyShip = new EnemyShip(Constants.ENEMY_SHIP_INIT_X + 50*j,Constants.ENEMY_SHIP_INIT_Y+ 50*i);
				this.enemyShips.add(enemyShip);
			}
		}
		
	}
	private void initializeVariables() {
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
		this.spaceShip= new SpaceShip();
		this.laser = new Laser();
		this.enemyShips = new ArrayList<>();
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
		this.laser.move();
		
		for(EnemyShip enemyShip: this.enemyShips) {
			
			//boundary conditions to change direction
			if(enemyShip.getX() >= Constants.BOARD_WIDTH- 2*Constants.BORDER_PADDING && direction!=-1 || enemyShip.getX() <= 2*Constants.BORDER_PADDING && direction!=1) {
				direction*=-1;
				
				
				Iterator<EnemyShip> iterator =enemyShips.iterator();
				while(iterator.hasNext()) {
					EnemyShip enemyship = iterator.next();
					enemyship.setY(enemyship.getY() + Constants.DOWN_SPEED);
				}
			}
			
			if(enemyShip.isVisible()) {
				enemyShip.move(direction);
			}
		}
	}
	
	public void drawPlayer(Graphics g) {
		g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
	}
	public void drawLaser(Graphics g) {
		if(!laser.isDead()) {
		g.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
	}
	}
	public void drawEnemyships(Graphics g) {
		for(EnemyShip enemyShip : this.enemyShips) {
			if(enemyShip.isVisible()) {
				g.drawImage(enemyShip.getImage(), enemyShip.getX(), enemyShip.getY(), this);	
			}

	}
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
			drawLaser(g);
			drawEnemyships(g);
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
		
		
		//shoot laser if spacebar is pressed
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE) {
			int laserX = this.spaceShip.getX();
			int laserY = this.spaceShip.getY();
			
			if(inGame && laser.isDead()) {
				laser =new Laser(laserX,laserY);
			}
		}
	}
	
}
