package com.draglantix.util;

import java.util.Random;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import com.draglantix.assets.PlayAssets;

public class Maths {

	private static Matrix4f projection;

	private static Random rand = new Random();

	public static Matrix4f createProjectionMatrix(float width, float height) {
		
		Matrix4f projection = new Matrix4f().setOrtho2D(-width / 2, width / 2, -height / 2, height / 2);

		Maths.projection = projection;

		return projection;
	}

	public static Matrix4f getFinalMatrix(Matrix4f transformationMatrix, boolean useCamera) {
		if(PlayAssets.camera != null && useCamera) {
			return (projection.mul(PlayAssets.camera.createViewMatrix())).mul(transformationMatrix);
		} else {
			return projection.mul(transformationMatrix);
		}
	}

	public static Matrix4f createTransformationMatrix(Vector2f translation, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.translate(translation.x, translation.y, 0);
		matrix.scale(scale);
		return matrix;
	}
	
	public static Vector2f RotatePoint(Vector2f orig, float dis, float deg) {
		float x = (float) ((Math.cos(Math.toRadians(deg)) * dis) + orig.x);
		float y = (float) ((Math.sin(Math.toRadians(deg)) * dis) + orig.y);
		
		return new Vector2f(x, y);
	}

	public static Random rand() {
		return rand;
	}
	
	public static float generateFloat(float amp) {
		return (rand.nextFloat() * amp) - (amp/2);
	}
	
	public static float clamp(float raw, float min, float max) {
		if(raw > max) {
			return max;
		}else if (raw < min) {
			return min;
		}else {
			return raw;
		}
	}
}
