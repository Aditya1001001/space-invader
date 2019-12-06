package com.applicationsupport.app;

import java.awt.EventQueue;
import com.applicationsupport.ui.GameMainFrame;
public class App {
	
	public static void main(String args[]) {
		EventQueue.invokeLater( ()-> {
			new GameMainFrame();
		});
	}

}
