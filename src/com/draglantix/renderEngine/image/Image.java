package com.draglantix.renderEngine.image;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.renderEngine.models.Texture;
import com.draglantix.renderEngine.objects.Objects;

public class Image extends Objects {

	public Image(Texture texture, Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color,
			boolean usesWorldPos) {
		super(texture, position, rotation, scale, color, usesWorldPos);

	}
	
	public Image(Objects obj) {
		super(obj);
	}

	@Override
	public void tick() {

	}

}
