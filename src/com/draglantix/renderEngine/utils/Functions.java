package com.draglantix.renderEngine.utils;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import com.draglantix.renderEngine.graphics.Graphics;

public class Functions {

	private static Matrix4f projection;

	public static Matrix4f createProjectionMatrix(float width, float height) {

		projection = new Matrix4f().setOrtho2D(-width / 2, width / 2, -height / 2, height / 2);

		return projection;
	}

	public static Matrix4f getFinalMatrixCamera(Matrix4f transformationMatrix, Graphics graphics) {
		return (projection.mul(graphics.getCurrentCamera().createViewMatrix())).mul(transformationMatrix);

	}

	public static Matrix4f getFinalMatrix(Matrix4f transformationMatrix) {
		return projection.mul(transformationMatrix);
	}

	public static Matrix4f createTransformationMatrix(Vector2f translation, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.translate(translation.x, translation.y, 0);
		matrix.scale(scale);
		return matrix;
	}
}
