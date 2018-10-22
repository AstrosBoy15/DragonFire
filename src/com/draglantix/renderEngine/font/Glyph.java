package com.draglantix.renderEngine.font;

import org.joml.Vector2f;

import com.draglantix.renderEngine.models.Texture;
import com.draglantix.renderEngine.objects.Objects;
import com.draglantix.renderEngine.utils.Color;

public class Glyph extends Objects{

	public Glyph(Texture texture, Vector2f position, Vector2f rotation, Vector2f scale, Color color, boolean usesWorldPos) {
		super(texture, position, rotation, scale, color, usesWorldPos);
	}
	
}
