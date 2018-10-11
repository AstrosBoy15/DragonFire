package com.draglantix.objects;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import com.draglantix.engine.ShaderProgram;

public abstract class ObjectShader extends ShaderProgram {

	private int location_finalMatrix;
	
	private int location_color;

	public ObjectShader(String key) {
		super(key);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocations() {
		location_finalMatrix = super.getUniformLocation("finalMatrix");
		location_color = super.getUniformLocation("color");
	}

	public void loadFinalMatrix(Matrix4f matrix) {
		super.loadMatrix(location_finalMatrix, matrix);
	}
	
	public void loadColor(Vector4f color) {
		super.loadVector4f(location_color, color);
	}

}