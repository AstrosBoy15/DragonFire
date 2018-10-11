package com.draglantix.entities;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.assets.PlayAssets;

public class Sheep extends Entity {

	public Sheep(int texture, Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color, float speed) {
		super(texture, position, rotation, scale, color, speed, PlayAssets.generateSquareBound(position, scale, true));
	}

	@Override
	public void tick() {
		super.tick();
	}
}
