package com.applicationsupport.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundFactory {
	
	private Clip clip;
	
	public SoundFactory() {
		
	}
	
	public void laser() {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File("D:\\Projects\\SpaceInvaders\\space-invaders\\sounds\\laser.wav"));
			clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
					
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void explosion() {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File("D:\\Projects\\SpaceInvaders\\space-invaders\\sounds\\explosion.wav"));
			clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
					
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
