package com.draglantix.assets;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.font.Message;
import com.draglantix.main.Configs;

public class MenuAssets {

	public static Message title, start, quit, version;
	
	public static void load() {
		title = new Message(Assets.font, "The Legend of the Dragon Hunter", new Vector2f(0, 100), new Vector2f(-3, 0), new Vector2f(10, 10), new Vector4f(1, 1, 1, 1));
		start = new Message(Assets.font, "Start", new Vector2f(0, 30), new Vector2f(360, 0), new Vector2f(10, 10), new Vector4f(1, 1, 1, 1));
		quit = new Message(Assets.font, "Quit", new Vector2f(0, 0), new Vector2f(0, 0), new Vector2f(10, 10), new Vector4f(1, 1, 1, 1));
		version = new Message(Assets.font, "Version: " + Configs.VERSION, new Vector2f(0, -250), new Vector2f(45, 0), new Vector2f(10, 10), new Vector4f(1, 0, 1, 1));
		addObjects();
	}

	public static void addObjects() {
		
	}

	public static void unload() {
		title.removeObjects();
		title = null;
		start.removeObjects();
		start = null;
		quit.removeObjects();
		quit = null;
		version.removeObjects();
		version = null;
	}

}
