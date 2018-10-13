package com.draglantix.renderEngine.models;

import org.joml.Vector2f;

import com.draglantix.renderEngine.utils.Timer;

public class Animation {
	private Texture[] frames;
	private int pointer;
	
	private double elapsedTime;
	private double fps;	
	
	private Timer timer;
	
	private boolean loop, hasPlayed = false;
	
	public Animation(int amount, int fps, String filename, boolean loop) {
		this.pointer = 0;
		this.elapsedTime = 0;
		this.fps = 1.0/(double)fps;
		timer = new Timer();
		this.frames = new Texture[amount];
		for(int i = 0; i < amount; i++) {
			this.frames[i] = new Texture(filename + i);
		}
		this.loop = loop;
	}
	
	public Animation(int width, int height, int scale, int fps, SpriteSheet sheet, boolean loop) {
		this.pointer = 0;
		this.elapsedTime = 0;
		this.fps = 1.0/(double)fps;
		timer = new Timer();
		this.frames = new Texture[width * height];
		for(int i = 0; i < frames.length; i++) {
			
			int x = (i/width) * scale;
			int y = (i % height) * scale;
		
			this.frames[i] = new Texture(sheet.crop(new Vector2f(y, x), new Vector2f(scale)));
		}
		this.loop = loop;
	}
	
	public Texture getTexture() {
		elapsedTime += timer.getDelta();
		
		if(elapsedTime>=fps) {
			elapsedTime = 0;
			pointer++;
		}
		
		if(pointer>=frames.length) {
			pointer = 0;
			hasPlayed = true;
		}
		
		if(!loop && hasPlayed) {
			return frames[frames.length-1];
		}else {
			return frames[pointer];
		}
	}
	
	public boolean hasPlayed() {
		return hasPlayed;
	}
}