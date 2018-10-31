package com.draglantix.renderEngine.objects;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import com.draglantix.renderEngine.shaders.ShaderProgram;

public abstract class ObjectShader extends ShaderProgram {

	private int location_finalMatrix;

	private int location_colorsLength;
	private int location_usesTex;

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
		location_colorsLength = super.getUniformLocation("colorsLength");
		location_usesTex = super.getUniformLocation("usesTex");
	}

	public void loadFinalMatrix(Matrix4f matrix) {
		super.loadMatrix(location_finalMatrix, matrix);
	}
	
	public void loadUsesTex(boolean useTex) {
		super.loadBoolean(location_usesTex, useTex);
	}
	
	public void loadColor(Vector4f color) {
		int loc_colors = super.getUniformLocation("colors[0]");
		super.loadVector4f(loc_colors, color);
		super.loadInt(location_colorsLength, 1);
	}
	
	public void loadColors(Vector4f[] colors) {
		for(int i = 0; i < colors.length; i++) {
			int loc_colors = super.getUniformLocation("colors["+i+"]");
			super.loadVector4f(loc_colors, colors[i]);
		}
		super.loadInt(location_colorsLength, colors.length);
	}

}