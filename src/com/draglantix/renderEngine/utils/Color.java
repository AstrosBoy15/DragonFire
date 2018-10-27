package com.draglantix.renderEngine.utils;

import org.joml.Vector4f;

public class Color {

	private float r, g, b, a;
	
	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.b = b;
		this.g = g;
		this.a = a;
	}
	
	public Color(Vector4f color) {
		r = color.x;
		b = color.y;
		g = color.z;
		a = color.w;
	}

	public Color getColor() {
		return this;
	}

	public float getAlpha() { return a; }
	public float getR() { return r; }
	public float getG() { return g; }
	public float getB() { return b; }
	public Vector4f getColorVector() { return new Vector4f(r, g, b, a); }
	public void setAlpha(float alpha) { a = alpha; }
	public void setR(float red) { r = red; }
	public void setG(float green) { g = green; }
	public void setB(float blue) { b = blue; }

}
