package com.draglantix.renderEngine.objects;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import com.draglantix.renderEngine.shaders.ShaderProgram;

public abstract class ObjectShader extends ShaderProgram {

	private int location_finalMatrix;

	private int location_colorType;
	private int location_c0;
	private int location_c1;
	private int location_c2;
	private int location_c3;
	private int location_c4;
	private int location_color;
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
		location_colorType = super.getUniformLocation("colorType");
		location_c0 = super.getUniformLocation("c0");
		location_c1 = super.getUniformLocation("c1");
		location_c2 = super.getUniformLocation("c2");
		location_c3 = super.getUniformLocation("c3");
		location_c4 = super.getUniformLocation("c4");
		location_color = super.getUniformLocation("color");
		location_usesTex = super.getUniformLocation("usesTex");
	}

	public void loadFinalMatrix(Matrix4f matrix) {
		super.loadMatrix(location_finalMatrix, matrix);
	}
	
	public void loadColorType(boolean useBlend) {
		super.loadBoolean(location_colorType, useBlend);
	}
	
	public void loadUsesTex(boolean useTex) {
		super.loadBoolean(location_usesTex, useTex);
	}
	
	public void loadColor(Vector4f color) {
		super.loadVector4f(location_color, color);
	}
	
	public void loadColors(Vector4f[] colors) {
		super.loadVector4f(location_c0, colors[0]);
		super.loadVector4f(location_c1, colors[1]);
		super.loadVector4f(location_c2, colors[2]);
		super.loadVector4f(location_c3, colors[3]);
		super.loadVector4f(location_c4, colors[4]);
	}

}