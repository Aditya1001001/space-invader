package com.applicationsupport.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.applicationsupport.callbacks.GameEventListener;
import com.applicationsupport.constants.Constants;
import com.applicationsupport.images.Image;
import com.applicationsupport.images.ImageFactory;
import com.applicationsupport.model.Bomb;
import com.applicationsupport.model.EnemyShip;
import com.applicationsupport.model.Laser;
import com.applicationsupport.model.SpaceShip;
import com.applicationsupport.sound.SoundFactory;
import com.sun.javafx.tk.FontMetrics;

public class GamePanel extends JPanel {
	

	private ImageIcon backgroundImage;
	private Timer timer; 
	private boolean inGame = true;
	private SpaceShip spaceShip;
	private Laser laser;
	private int direction =-1;
	private List<EnemyShip> enemyShips;
	private List<Bomb> bombs;
	private Random generator;
	private String message;
	private int deaths;
	private SoundFactory soundFactory;
	
	
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
		this.soundFactory = new SoundFactory();
		this.spaceShip= new SpaceShip();
		this.laser = new Laser();
		this.enemyShips = new ArrayList<>();
		this.bombs = new ArrayList<>();
		this.generator = new Random();
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
		
		//condition for game end
		if(spaceShip.isDead()) {
			inGame = false;
			message = Constants.GAME_OVER;
		}
		
		//condition for winning
		if(deaths == this.enemyShips.size()) {
			inGame = false;
			message = Constants.WIN;
		}
		this.spaceShip.move();
		
		if(!laser.isDead()) {
			//collision between laser and enemy ships
			int laserX = laser.getX();
			int laserY = laser.getY();
			for(EnemyShip enemyShip: this.enemyShips) {
				if(!enemyShip.isVisible())
					continue;
				int enemyX = enemyShip.getX();
				int enemyY = enemyShip.getY();
				if(laserX >= enemyX && laserX <= (enemyX + Constants.ENEMY_SHIP_WIDTH) && laserY >= enemyY && laserY <= enemyY + Constants.ENEMY_SHIP_HEIGHT) {
					soundFactory.explosion();
					enemyShip.setVisible(false);
					laser.die();
					this.deaths++;
					}
				
			}
			this.laser.move();
		}
		
		
		//moving enemy ships
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
				
				//if the enemy ships reach the bottom, invasion successful so instant death
				if(enemyShip.getY() > Constants.BOARD_HEIGHT - (100 + Constants.SPACESHIP_HEIGHT)) {
					spaceShip.die();
				}
				enemyShip.move(direction);
			}
		}
		
		//dropping bombs
		for(EnemyShip enemyShip: this.enemyShips) {
			if(enemyShip.isVisible() && generator.nextDouble() < Constants.BOMB_PROBABILITY) {
				Bomb bomb = new Bomb(enemyShip.getX(), enemyShip.getY());
				this.bombs.add(bomb);
			}
		}
		
		//moving bombs
		for(Bomb bomb: this.bombs){
			
			int bombX = bomb.getX();
			int bombY = bomb.getY();
			int spaceshipX = spaceShip.getX();
			int spaceshipY = spaceShip.getY();
			int laserX = laser.getX();
			int laserY = laser.getY();
			
			// collision between laser and bombs
			if(!bomb.isDead() && !laser.isDead()) {
				// bigger than sprite hit box size to make bombs easier to hit
				if(bombX + 3 >= laserX && bombX - 3 <= (laserX + 3) && bombY >= laserY && bombY <= laserY + 7) {
					soundFactory.explosion();
					bomb.die();
					laser.die();
				}
					
			}
			
			//collision between bomb and player ship
			if(!bomb.isDead() && !spaceShip.isDead()) {
				if(bombX >= spaceshipX && bombX <= (spaceshipX + Constants.SPACESHIP_WIDTH) && bombY >= spaceshipY && bombY <= spaceshipY + Constants.SPACESHIP_HEIGHT) {
					soundFactory.explosion();
					bomb.die();
					spaceShip.die();
				}
			}
			
			if(!bomb.isDead()) {
				bomb.move();
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
	private void drawBombs(Graphics g) {
		for(Bomb bomb : this.bombs) {
			if(!bomb.isDead()) {
				g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);	
			}

	}
	}
	private void drawGameOver(Graphics g) {
		
		g.drawImage(backgroundImage.getImage(), 0, 0, this);
		Font font = new Font("Helvetica", Font.BOLD, 50);
		java.awt.FontMetrics fontmetrics = this.getFontMetrics(font);
		g.setColor(Color.GRAY);
		g.setFont(font);		
		g.drawString(message, (Constants.BOARD_WIDTH - fontmetrics.stringWidth(message))/2, Constants.BOARD_HEIGHT / 2 - 100);

	
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
			drawBombs(g);
		} 
		else {
			if(timer.isRunning()) {
				timer.stop();
			}
			drawGameOver(g);
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
				soundFactory.laser();
				laser =new Laser(laserX,laserY);
			}
		}
	}
	
}
