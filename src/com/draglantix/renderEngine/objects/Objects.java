package com.draglantix.renderEngine.objects;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

import com.draglantix.renderEngine.models.SpriteSheet;

public abstract class Objects {

	protected int texture;
	protected SpriteSheet sheet;

	protected Vector2f position, rotation, scale;
	protected Matrix4f transformation;

	protected Vector4f color;

	protected boolean usesWorldPos;

	public Objects(int texture, Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color,
			boolean usesWorldPos) {
		this.texture = texture;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.color = color;
		this.usesWorldPos = usesWorldPos;
		updateTransformationMatrix(position, rotation, scale);
	}

	public Objects(SpriteSheet sheet, Vector2f position, Vector2f rotation, Vector2f scale, Vector4f color,
			boolean usesWorldPos) {
		this.sheet = sheet;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.color = color;
		this.usesWorldPos = usesWorldPos;
		updateTransformationMatrix(position, rotation, scale);
	}

	public Objects(Objects obj) {
		this.texture = obj.texture;
		this.position = obj.position;
		this.rotation = obj.rotation;
		this.scale = obj.scale;
		this.color = obj.color;
		this.usesWorldPos = obj.usesWorldPos;
		updateTransformationMatrix(position, rotation, scale);
	}

	public abstract void tick();

	public int getTexture() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public SpriteSheet getSheet() {
		return sheet;
	}

	public void setSheet(SpriteSheet sheet) {
		this.sheet = sheet;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getRotation() {
		return rotation;
	}

	public void setRotation(Vector2f rotation) {
		this.rotation = rotation;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

	public Vector4f getColor() {
		return color;
	}

	public void setColor(Vector4f color) {
		this.color = color;
	}

	public void setR(float red) {
		this.color.x = red;
	}

	public void setG(float green) {
		this.color.y = green;
	}

	public void setB(float blue) {
		this.color.z = blue;
	}

	public void setA(float alpha) {
		this.color.w = alpha;
	}

	public Matrix4f updateTransformationMatrix(Vector2f translation, Vector2f rot, Vector2f scale) {
		Matrix4f transformation = new Matrix4f();
		transformation.translate(translation.x, translation.y, 0);
		transformation.rotate((float) Math.toRadians(rot.x), 0.0f, 0.0f, 1.0f);
		transformation.rotate((float) Math.toRadians(rot.y), 0.0f, 1.0f, 0.0f);
		transformation.scale(scale.x, scale.y, 0);

		this.transformation = transformation;

		return transformation;
	}

	public Matrix4f getTransformationMatrix() {
		return transformation;
	}

	public boolean doesUseWorldPos() {
		return usesWorldPos;
	}
	
}
