package com.draglantix.renderEngine.rectangle;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.renderEngine.objects.Objects;

public class Rectangle extends Objects {

	public Rectangle(int texture, Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color,
			boolean usesWorldPos) {
		super(texture, position, rotation, scale, color, usesWorldPos);

	}

	public Rectangle(Objects object) {
		super(object);

	}

	@Override
	public void tick() {

	}

}
