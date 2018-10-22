package com.draglantix.renderEngine.utils;

import org.joml.Vector4f;

public class Color {

	private float r, g, b, a;
	private boolean converted = false;

	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.b = b;
		this.g = g;
		this.a = a;
		convert();
	}
	
	public Color(Vector4f color) {
		color.x = r;
		color.y = b;
		color.z = g;
		color.w = a;
		convert();
	}

	public Color getColor() {
		return this;
	}
	
	public Vector4f getColorVector() {
		return new Vector4f(r, g, b, a);
	}

	public float getAlpha() {
		if(converted) {
			return a * 255;
		} else {
			return a;
		}
	}

	public float getR() {
		if(converted) {
			return r * 255;
		} else {
			return r;
		}
	}

	public float getG() {
		if(converted) {
			return g * 255;
		} else {
			return g;
		}
	}

	public float getB() {
		if(converted) {
			return b * 255;
		} else {
			return b;
		}
	}

	public void setAlpha(float alpha) {
		a = alpha;
	}

	public void setR(float red) {
		r = red;
		convert();
	}

	public void setG(float green) {
		g = green;
		convert();
	}

	public void setB(float blue) {
		b = blue;
		convert();
	}

	public void convert() {
		if(r > 1 || g > 1 || b > 1) {
			r /= 255;
			g /= 255;
			b /= 255;
			converted = true;
		}
	}

}
