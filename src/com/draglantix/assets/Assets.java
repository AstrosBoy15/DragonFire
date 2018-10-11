package com.draglantix.assets;

import com.draglantix.font.Font;
import com.draglantix.models.Loader;
import com.draglantix.models.RawModel;
import com.draglantix.models.SpriteSheet;
import com.draglantix.states.State;

public class Assets {

	public static RawModel quad;
	public Loader loader;
	
	private static SpriteSheet fontTex;
	public static Font font;
	
	public Assets() {
		loader = new Loader();
		float[] positions = { -1, 1, -1, -1, 1, 1, 1, -1 };
		quad = loader.loadToVAO(positions);
		
		fontTex = new SpriteSheet("font");
		font = new Font(fontTex, 32);
	}
	
	public void load(State state) {
		switch(state) {
		case INTRO:
			IntroAssets.load();
			break;
		case PLAY:
			PlayAssets.load();
			break;
		default:
			MenuAssets.load();
			break;
		}
		
	}
	
	public void unload(State state) {
		switch(state) {
		case INTRO:
			IntroAssets.unload();
			break;
		case PLAY:
			PlayAssets.unload();
			break;
		default:
			MenuAssets.unload();
			break;
		}
	}
}
