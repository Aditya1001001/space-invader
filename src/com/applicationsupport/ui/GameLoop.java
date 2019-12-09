package com.applicationsupport.ui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameLoop implements ActionListener{
	
	private GamePanel gamePanel;
	
	public GameLoop(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.gamePanel.doOneLoop();
	}
		
}
