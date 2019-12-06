package com.applicationsupport.ui;

import javax.swing.JFrame;

import com.applicationsupport.constants.Constants;
import com.applicationsupport.images.Image;
import com.applicationsupport.images.ImageFactory;

public class GameMainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GameMainFrame() {
		initializeLayout();
	}
	public void initializeLayout() {
		add(new GamePanel());
		setIconImage(ImageFactory.createImage(Image.SPACESHIP).getImage());
		setTitle(Constants.TITLE);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
