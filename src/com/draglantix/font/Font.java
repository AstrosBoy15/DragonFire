package com.draglantix.font;

import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2f;

import com.draglantix.models.SpriteSheet;
import com.draglantix.models.Texture;

public class Font {

	private static String glyphs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!?,/%$()+-:;'* ";
	
	private static Map<Character, Texture> fontData;
	
	private SpriteSheet sheet;
	private int tileSize;
	private int rows;
	
	public Font(SpriteSheet sheet, int tileSize) {
		this.sheet = sheet;
		this.tileSize = tileSize;
		rows = sheet.getWidth() / tileSize;
		fontData = new HashMap<Character, Texture>();
		loadFont();
	}
	
	private void loadFont() {
		Vector2f scale, pos;
		for(int i = 0; i < glyphs.length(); i ++) {
			
			//TODO replace "-2" with custom spacing from fnt file
			scale = new Vector2f(tileSize - 2, tileSize);	
			pos = new Vector2f((i % rows) * tileSize, (int) (i / rows) * tileSize);				
			
			fontData.put(glyphs.charAt(i), 
					new Texture(sheet.crop(pos, scale)));
		}
	}
	
	public Texture getGlyphTexture(char i) {
		return fontData.get(i);
	}
	
	public int getSize() {
		return tileSize;
	}
	
	

}
