package com.draglantix.renderEngine.image;

import org.joml.Vector2f;

import com.draglantix.renderEngine.models.Texture;
import com.draglantix.renderEngine.objects.Objects;
import com.draglantix.renderEngine.utils.Color;

public class Image extends Objects {

	public Image(Texture texture, Vector2f position, Vector2f rotation, Vector2f scale, Color color,
			boolean usesWorldPos) {
		super(texture, position, rotation, scale, color, usesWorldPos);

	}
	
	public Image(Texture texture, Vector2f position, Vector2f rotation, Vector2f scale, Color[] colors,
			boolean usesWorldPos) {
		super(texture, position, rotation, scale, colors, usesWorldPos);

	}
	
	public Image(Objects obj) {
		super(obj);
	}

	public void tick() {

	}

}
