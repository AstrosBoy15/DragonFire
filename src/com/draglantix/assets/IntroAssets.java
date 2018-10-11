package com.draglantix.assets;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.engine.Engine;
import com.draglantix.guis.Gui;
import com.draglantix.models.Texture;

public class IntroAssets {

	public static Texture logoTex;

	public static Gui logo;

	public static void load() {
		logoTex = new Texture("draglantix");
		logo = new Gui(IntroAssets.logoTex.getTextureID(), new Vector2f(0, 0), new Vector2f(0, 0),
				new Vector2f(320, 40), new Vector4f(1f, 0, 1f, 0));
		addObjects();
	}

	public static void addObjects() {
		Engine.addObject(Gui.class, logo);
	}

	public static void unload() {
		logoTex = null;
		Engine.removeObject(Gui.class, logo);
	}

}
