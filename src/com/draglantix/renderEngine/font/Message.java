package com.draglantix.renderEngine.font;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.draglantix.renderEngine.graphics.Graphics;
import com.draglantix.renderEngine.models.Texture;
import com.draglantix.renderEngine.utils.Color;
import com.draglantix.renderEngine.utils.Functions;

public class Message {

	private Font font;
	private String msg;

	private Vector2f pos, rot, scale, orig;
	private Color color;

	private List<Glyph> glyphs = new ArrayList<Glyph>();
	private List<Float> offsets = new ArrayList<Float>();

	private float totalLength = 0;

	private boolean useWorldPos;

	public Message(String msg, Vector2f pos, Vector2f rot, Vector2f scale, Color color, boolean useWorldPos,
			Graphics graphics) {
		this.font = graphics.getFont();
		this.msg = msg.toUpperCase();
		this.pos = pos;
		this.scale = scale;
		this.rot = rot;
		this.color = color;
		this.useWorldPos = useWorldPos;
		addObjects();
	}

	public void addObjects() {
		for(int i = 0; i < msg.length(); i++) {
			Texture texture = font.getGlyphTexture(msg.charAt(i));
			float ratio = scale.x / texture.getWidth();
			float offset = (texture.getWidth() + 35) * ratio;
			Vector2f finalPos = new Vector2f((pos.x + (i * offset)), pos.y);
			if(i != 0) {
				finalPos = Functions.RotatePoint(orig, finalPos.x - orig.x, rot.x);
				offsets.add(finalPos.x / i);
			} else {
				orig = pos;
			}
			glyphs.add(new Glyph(texture, finalPos, rot, scale, color, useWorldPos));
		}

		for(float offset : offsets) {
			totalLength += offset;
		}

		for(Glyph g : glyphs) {
			g.setPosition(new Vector2f(g.getPosition().x - (totalLength / 2), g.getPosition().y));
		}
	}

	public List<Glyph> getGlyphs() {
		return glyphs;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getFont() {
		return font;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setR(float red) {
		this.color.setR(red);
	}

	public void setG(float green) {
		this.color.setG(green);
	}

	public void setB(float blue) {
		this.color.setB(blue);
	}

	public void setA(float alpha) {
		this.color.setAlpha(alpha);
	}
}
