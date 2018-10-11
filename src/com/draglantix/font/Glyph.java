package com.draglantix.font;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.objects.Objects;

public class Glyph extends Objects{

	public Glyph(int texture, Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color) {
		super(texture, position, rotation, scale, color, null);
	}

	@Override
	public void tick() {
		
	}
	
}
