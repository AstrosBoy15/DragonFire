package com.draglantix.renderEngine.engine;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public interface ICamera {
	
	public void update();
	public void move();
	public Matrix4f createViewMatrix();
	public Vector2f getPosition();
	public float getZoom();

}
