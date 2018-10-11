package com.draglantix.font;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.engine.Engine;
import com.draglantix.models.Texture;
import com.draglantix.util.Maths;

public class Message {

	private Font font;
	private String msg;
	
	private Vector2f pos, rot, scale, orig;
	private Vector4f color;
	
	private List<Glyph> glyphs = new ArrayList<Glyph>();
	private List<Float> offsets = new ArrayList<Float>();
	
	private float totalLength = 0;
	
	public Message(Font font, String msg, Vector2f pos, Vector2f rot, Vector2f scale, Vector4f color) {
		this.font = font;
		this.msg = msg.toUpperCase();
		this.pos = pos;
		this.scale = scale;
		this.rot = rot;
		this.color = color;
		addObjects();
	}
	
	public void addObjects() {
		for(int i = 0; i < msg.length(); i ++) {
			Texture texture = font.getGlyphTexture(msg.charAt(i));
			float ratio = scale.x / texture.getWidth();
			float offset = (texture.getWidth() + 35) * ratio;
			Vector2f finalPos = new Vector2f((pos.x + (i * offset)), pos.y);
			if(i != 0) {
				finalPos = Maths.RotatePoint(orig, finalPos.x - orig.x, rot.x);
				offsets.add(finalPos.x/i);
			}else {
				orig = pos;
			}
			glyphs.add(new Glyph(texture.getTextureID(), finalPos, rot, scale, color));
		}
		
		for(float offset : offsets) {
			totalLength += offset;
		}
		
		for(Glyph g : glyphs) {
			g.setPosition(new Vector2f(g.getPosition().x - (totalLength/2), g.getPosition().y));
			Engine.addObject(Glyph.class, g);
		}
	}
	
	public void removeObjects() {
		for(Glyph g : glyphs) {
			Engine.removeObject(Glyph.class, g);
		}
	}
	
}
