package com.applicationsupport.constants;

public class Constants {
	private Constants() {
		
	}
	
	public static final String TITLE = "Space Invaders";
	
	public static final int BOARD_WIDTH =900;
	public static final int BOARD_HEIGHT =750;
	
	//speed of application
	public static final int GAME_SPPED = 25;
	
	//spaceship constants
	public static final int SPACESHIP_WIDTH = 30;
	public static final int SPACESHIP_HEIGHT = 30;
	
	//laser speed
	public static final int LASER_VERTICAL_SPEED = 5;
	
	//Enemy ship constants
	public static final int ENEMY_SHIP_HEIGHT = 24;
	public static final int ENEMY_SHIP_WIDTH = 33;
	public static final int ENEMY_SHIP_INIT_X = 250;
	public static final int ENEMY_SHIP_INIT_Y = 100;
	public static final int ENEMY_SHIPS_ROW = 4;
	public static final int ENEMY_SHIPS_COLUMN = 8;
	public static final int BORDER_PADDING = 34;
	public static final int DOWN_SPEED = 25;
	
	//bomb
	public static final int BOMB_HEIGHT = 8;
	public static final double BOMB_PROBABILITY = .0004;
	
	
	
	//images
	public static final String UFO_IMAGE_URL = "D:\\Projects\\SpaceInvaders\\space-invaders\\images\\ufo.png";
	public static final String LASER_IMAGE_URL = "D:\\Projects\\SpaceInvaders\\space-invaders\\images\\laser.png";
	public static final String BOMB_IMAGE_URL = "D:\\Projects\\SpaceInvaders\\space-invaders\\images\\bomb.png";
	public static final String BACKGROUND_IMAGE_URL = "D:\\Projects\\SpaceInvaders\\space-invaders\\images\\background.jpg";
	public static final String SPACESHIP_URL = "D:\\Projects\\SpaceInvaders\\space-invaders\\images\\spaceship.png";

	public static final String GAME_OVER = "GAME OVER!";
	public static final String WIN = "YOU WIN!";


	
	

	
}
